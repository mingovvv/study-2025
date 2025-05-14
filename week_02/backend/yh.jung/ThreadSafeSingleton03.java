package backend.yh.jung;

public class ThreadSafeSingleton03 {
    private static ThreadSafeSingleton03 instance;
    private long currentId = 0L;

    private ThreadSafeSingleton03() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGenerator 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static synchronized ThreadSafeSingleton03 getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton03();
        }
        return instance;
    }

    public long getNextId() {
        return ++currentId;
    }
}
