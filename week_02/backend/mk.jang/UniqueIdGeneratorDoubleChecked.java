import java.util.concurrent.atomic.AtomicLong;

/**
 * [더블 체킹 방식]
 */
public class UniqueIdGeneratorDoubleChecked {

    // volatile 키워드 필수: instance 변수의 가시성 및 명령어 재배치 방지
    private static volatile UniqueIdGeneratorDoubleChecked instance;

    private final AtomicLong currentId = new AtomicLong(0L);

    private UniqueIdGeneratorDoubleChecked() {
        System.out.println("[" + Thread.currentThread().getName() + "] UniqueIdGeneratorDoubleChecked 인스턴스 생성됨. HashCode: " + this.hashCode());
    }

    public static UniqueIdGeneratorDoubleChecked getInstance() {
        if (instance == null) { // 첫 번째 null 체크 (성능 향상)
            synchronized (UniqueIdGeneratorDoubleChecked.class) { // 동기화 블록
                if (instance == null) { // 두 번째 null 체크 (스레드 안전성 확보)
                    instance = new UniqueIdGeneratorDoubleChecked();
                }
            }
        }
        return instance;
    }

    public long getNextId() {
        return currentId.incrementAndGet();
    }

}