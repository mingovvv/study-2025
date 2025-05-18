package backend.backendweb.week_02.yh.jung;

import java.io.Serializable;

public class UniqueIdGenerator implements Serializable {

    private static final long serialVersionUID = 1L;

    private static UniqueIdGenerator instance;
    private long currentId = 0L;

    private UniqueIdGenerator() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGenerator 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static UniqueIdGenerator getInstance() {
        if (instance == null) {
            instance = new UniqueIdGenerator();
        }
        return instance;
    }

    public long getNextId() {
        return ++currentId;
    }

}
