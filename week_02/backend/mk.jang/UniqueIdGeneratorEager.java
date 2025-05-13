import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

// 이른초기화 방식: 구현 간단하지만 지연 초기화가 안됨
public class UniqueIdGeneratorEager implements Serializable {

    private static final long serialVersionUID = 1L;

    // 이른 초기화: 클래스 로딩 시점에 인스턴스 생성
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