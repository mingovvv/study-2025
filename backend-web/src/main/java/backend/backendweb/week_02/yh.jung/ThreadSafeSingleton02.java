package backend.backendweb.week_02.yh.jung;

public class ThreadSafeSingleton02 {
    private static ThreadSafeSingleton02 instance;
    private long currentId = 0L;

    static {
        try {
            instance = new ThreadSafeSingleton02();
        } catch (Exception e) {
            throw new RuntimeException("싱글톤 객체 생성 오류");
        }
    }

    private ThreadSafeSingleton02() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGenerator 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static ThreadSafeSingleton02 getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton02();
        }
        return instance;
    }

    public long getNextId() {
        return ++currentId;
    }
}
