import java.util.concurrent.atomic.AtomicLong;

/**
 * [정적 내부 중첩 클래스 방식]
 *  - getInstance() 호출 시점에 내부 클래스 로드되어 지연 초기화 가능
 */
public class UniqueIdGeneratorStaticInner {

    private final AtomicLong currentId = new AtomicLong(0L);

    private UniqueIdGeneratorStaticInner() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGeneratorStaticInner 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    // 정적 내부 중첩 클래스 홀더
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