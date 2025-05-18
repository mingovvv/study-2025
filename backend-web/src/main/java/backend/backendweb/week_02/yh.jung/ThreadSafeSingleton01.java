package backend.backendweb.week_02.yh.jung;

public class ThreadSafeSingleton01 {
    private static ThreadSafeSingleton01 instance;
    private long currentId = 0L;

    private ThreadSafeSingleton01() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGenerator 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static ThreadSafeSingleton01 getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton01();
        }
        return instance;
    }

    public long getNextId() {
        return ++currentId;
    }
}

