import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * [이른 초기화 방식]
 * - 클래스 로딩 시점에 인스턴스 생성
 */
public class UniqueIdGeneratorEager {

    private static final UniqueIdGeneratorEager INSTANCE = new UniqueIdGeneratorEager();

    private final AtomicLong currentId = new AtomicLong(0L);

    private UniqueIdGeneratorEager() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGeneratorEager 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static UniqueIdGeneratorEager getInstance() {
        return INSTANCE;
    }

    public long getNextId() {
        return currentId.incrementAndGet();
    }

}