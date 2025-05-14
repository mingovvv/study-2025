package backend.yh.jung;

public class ThreadSafeSingleton04 {
    private static ThreadSafeSingleton04 instance;
    private long currentId = 0L;

    private static class ThreadSafeSingleton04Holder {
        private static final ThreadSafeSingleton04 INSTANCE = new ThreadSafeSingleton04();
    }

    private ThreadSafeSingleton04() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGenerator 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static ThreadSafeSingleton04 getInstance() {
        return ThreadSafeSingleton04Holder.INSTANCE;
    }

    public long getNextId() {
        return ++currentId;
    }
}
