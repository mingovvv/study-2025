import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

// 정적내부클래스 방식: 지연 초기화가 가능하여 getInstance() 메소드가 처음 호출될 때, SingletonHolder 클래스가 로드되면서 인스턴스 생성된다.
public class UniqueIdGeneratorStaticInner implements Serializable {

    private static final long serialVersionUID = 1L;

    private final AtomicLong currentId = new AtomicLong(0L);

    private UniqueIdGeneratorStaticInner() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGeneratorStaticInner 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    // 정적 내부 클래스 홀더
    private static class SingletonHolder {
        private static final UniqueIdGeneratorStaticInner INSTANCE = new UniqueIdGeneratorStaticInner();
    }

    public static UniqueIdGeneratorStaticInner getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public long getNextId() {
        return currentId.incrementAndGet();
    }

}