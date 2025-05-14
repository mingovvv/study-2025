package backend.yh.jung;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Singleton {
    public static void main(String[] args) throws Exception {

        // 1. 일반적인 케이스
        System.out.println("------------ 1. 일반적인 케이스 ------------");
        UniqueIdGenerator uniqueIdGenerator11 = UniqueIdGenerator.getInstance();
        UniqueIdGenerator uniqueIdGenerator12 = UniqueIdGenerator.getInstance();

        System.out.println(uniqueIdGenerator11.hashCode() + " " + uniqueIdGenerator12.hashCode());


        // 2. 직렬화로 인한 깨짐
        // -> 역직렬화 자체가 보이지 않은 생성자로서 역할을 수행하기 때문에 인스턴스를 또다시 만들어, 직렬화에 사용한 인스턴스와는 전혀 다른 인스턴스가 되기 때문에 일어나는 것)
        System.out.println("\n------------ 2. 직렬화로 인한 깨짐 ------------");
        UniqueIdGenerator uniqueIdGenerator21 = UniqueIdGenerator.getInstance();

        String fileName = "./week_02/backend/yh.jung/singleton.obj";

        // 직렬화
        ObjectOutputStream out21 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        out21.writeObject(uniqueIdGenerator21);
        out21.close();

        // 역직렬화
        ObjectInputStream in22 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        UniqueIdGenerator uniqueIdGenerator22 = (UniqueIdGenerator) in22.readObject();
        in22.close();

        System.out.println(uniqueIdGenerator21.hashCode() + " " + uniqueIdGenerator22.hashCode());


        // 3. 2번 대응 방안
        // -> readResolve(), 인스턴스 필드 transient 선언
        System.out.println("\n------------ 3. 2번 대응 방안 ------------");
        Singleton03 singleton31 = Singleton03.getInstance();

        // 직렬화
        ObjectOutputStream out31 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        out31.writeObject(singleton31);
        out31.close();

        // 역직렬화
        ObjectInputStream in32 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
        Singleton03 singleton32 = (Singleton03) in32.readObject();
        in32.close();

        System.out.println(singleton31.hashCode() + " " + singleton32.hashCode());


        // 4. reflection으로 인한 깨짐
        // -> 리플렉션을 통해 싱글톤 객체를 생성하게 되면 다른 객체를 반환해 싱글톤이 다시 한번 깨지는 것
        System.out.println("\n------------ 4. reflection으로 인한 깨짐 ------------");
        
        // 1. Singleton의 Class에서 생성자를 가져옴
        Constructor<UniqueIdGenerator> consructor = UniqueIdGenerator.class.getDeclaredConstructor();

        // 2. 생성자가 private 이기 때문에 외부에서 access 할 수 있도록 true 설정
        consructor.setAccessible(true);

        // 3. 인스턴스를 생성
        UniqueIdGenerator uniqueIdGenerator41 = consructor.newInstance();
        UniqueIdGenerator uniqueIdGenerator42 = consructor.newInstance();

        System.out.println(uniqueIdGenerator41.hashCode() + " " + uniqueIdGenerator42.hashCode());

        // 5. 4번 대응 방안
        // -> enum 싱글톤 (enum은 애초에 멤버를 만들때 private로 만들고 한번만 초기화 하기 때문에 Thread-Safe, 기본적으로 serializable 인터페이스를 구현)
        System.out.println("\n------------ 5. 4번 대응 방안 ------------");
        EnumSingleton enumSingleton51 = EnumSingleton.INSTANCE;
        EnumSingleton enumSingleton52 = EnumSingleton.INSTANCE;

        System.out.println(enumSingleton51.hashCode() + " " + enumSingleton52.hashCode());


        // 6. thread-safe
        System.out.println("\n------------ 6. thread-safe ------------");
        // 6-1. 기본
        System.out.println("\n----- 6-1. 기본 -----");
        ThreadSafeSingleton01 threadSafeSingleton11 = ThreadSafeSingleton01.getInstance();
        ThreadSafeSingleton01 threadSafeSingleton12 = ThreadSafeSingleton01.getInstance();
        System.out.println(threadSafeSingleton11.hashCode() + " " + threadSafeSingleton12.hashCode());

        // 6-2. static block
        System.out.println("\n----- 6-2. static block -----");
        ThreadSafeSingleton02 threadSafeSingleton21 = ThreadSafeSingleton02.getInstance();
        ThreadSafeSingleton02 threadSafeSingleton22 = ThreadSafeSingleton02.getInstance();
        System.out.println(threadSafeSingleton21.hashCode() + " " + threadSafeSingleton22.hashCode());

        // 6-3. Lazy initialization
        // -> thread 문제
        System.out.println("\n----- 6-3. thread 문제 -----");
        Test[] threadSafeSingleton63 = new Test[10];

        ExecutorService service63 = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int num = i;
            service63.submit(() -> {
                threadSafeSingleton63[num] = Test.getInstance();
            });
        }
        service63.shutdown();
        service63.awaitTermination(1, TimeUnit.SECONDS);

        for(Test s : threadSafeSingleton63) {
            System.out.println(s.hashCode());
        }

        // 6-4. Thread safe initialization
        // -> overhead 이슈
        System.out.println("\n----- 6-4. Thread safe initialization -----");
        ThreadSafeSingleton03[] threadSafeSingleton64 = new ThreadSafeSingleton03[10];

        ExecutorService service64 = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int num = i;
            service64.submit(() -> {
                threadSafeSingleton64[num] = ThreadSafeSingleton03.getInstance();
            });
        }
        service64.shutdown();
        service64.awaitTermination(1, TimeUnit.SECONDS);


        for(ThreadSafeSingleton03 s : threadSafeSingleton64) {
            System.out.println(s.hashCode());
        }


        // 6-5. Bill Pugh Solution (LazyHolder)
        // -> Reflection API, 직렬화/역직렬화를 통해 깨질 수 있음
        System.out.println("\n----- 6-5. Bill Pugh Solution -----");
        ThreadSafeSingleton04[] threadSafeSingleton65 = new ThreadSafeSingleton04[10];

        ExecutorService service65 = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int num = i;
            service65.submit(() -> {
                threadSafeSingleton65[num] = ThreadSafeSingleton04.getInstance();
            });
        }
        service65.shutdown();
        service65.awaitTermination(1, TimeUnit.SECONDS);

        for(ThreadSafeSingleton04 s : threadSafeSingleton65) {
            System.out.println(s.hashCode());
        }
    }
}
