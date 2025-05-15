import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1. 싱글톤 깨짐 이슈
 */
public class SingletonBrokenMain {

    public static void main(String[] args) throws InterruptedException {

        int numberOfThreads = 100; // 동시에 실행할 스레드 수
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        Set<Integer> instanceHashCodes = new HashSet<>();

        System.out.println("테스트 시작: " + numberOfThreads + "개의 스레드에서 UniqueIdGenerator.getInstance() 호출");

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
//                UniqueIdGenerator generator = UniqueIdGenerator.getInstance();
                UniqueIdGeneratorEager generator = UniqueIdGeneratorEager.getInstance();
                instanceHashCodes.add(generator.hashCode());
            });
        }

        executorService.shutdown();

        System.out.println("========================================");
        System.out.println("테스트 결과:");
        System.out.println("생성된 UniqueIdGenerator 인스턴스 해시코드 개수: " + instanceHashCodes.size());

        if (instanceHashCodes.size() > 1) {
            System.out.println("[싱글톤 깨짐]");
            instanceHashCodes.forEach(s -> System.out.println(" - 생성된 인스턴스 HashCode: " + s.hashCode()));
        } else if (instanceHashCodes.size() == 1) {
            System.out.println("[싱글톤 유지]");
            System.out.println(" - 생성된 인스턴스 HashCode: " + instanceHashCodes.iterator().next().hashCode());
        }
        System.out.println("========================================");


    }

}
