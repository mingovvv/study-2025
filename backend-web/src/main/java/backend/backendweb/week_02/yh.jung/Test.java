package backend.backendweb.week_02.yh.jung;

public class Test {
    private static Test instance;
    private long currentId = 0L;

    private Test() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGenerator 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static Test getInstance() {
        if (instance == null) {
            instance = new Test();
        }
        return instance;
    }

    public long getNextId() {
        return ++currentId;
    }
}
