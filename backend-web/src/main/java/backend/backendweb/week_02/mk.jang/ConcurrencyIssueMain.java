package backend.backendweb.week_02.mk.jang;// GetNextIdConcurrencyTest_NoLatch.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyIssueMain {

    public static void main(String[] args) {
        // 테스트할 ID 생성기 인스턴스 생성
         final UniqueIdGenerator idGenerator = UniqueIdGenerator.getInstance();
//        final UniqueIdGeneratorEager idGenerator = UniqueIdGeneratorEager.getInstance();
//         final UniqueIdGeneratorStaticInner idGenerator = UniqueIdGeneratorStaticInner.getInstance();
//         final UniqueIdGeneratorDoubleChecked idGenerator = UniqueIdGeneratorDoubleChecked.getInstance();

        System.out.println("테스트에 사용될 단일 UniqueIdGenerator 인스턴스. HashCode: " + idGenerator.hashCode());
        System.out.println("----------------------------------------------------");

        int numberOfThreads = 100;    // 동시에 ID를 요청할 스레드 수
        int idsPerThread = 1000;      // 각 스레드가 요청할 ID 개수
        int totalExpectedIds = numberOfThreads * idsPerThread;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        // 생성된 모든 ID를 저장할 리스트
        List<Long> generatedIds = Collections.synchronizedList(new ArrayList<>(totalExpectedIds));

        System.out.println(numberOfThreads + "개의 스레드가 각각 " + idsPerThread + "개의 ID를 요청합니다.");
        System.out.println("예상되는 총 ID 개수: " + totalExpectedIds);
        System.out.println("모든 스레드는 위에서 생성된 단일 UniqueIdGenerator 인스턴스를 사용합니다.");
        System.out.println("----------------------------------------------------");

        // 작업 제출
        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    // startLatch.await() 제거됨. 스레드 풀에 의해 바로 실행 시도.
                    for (int j = 0; j < idsPerThread; j++) {
                        // 동일한 idGenerator 인스턴스의 getNextId() 호출
                        long id = idGenerator.getNextId();
                        generatedIds.add(id);
                    }
                } catch (Exception e) { // 스레드 내에서 발생하는 예외 처리
                    System.err.println(Thread.currentThread().getName() + "에서 작업 중 예외 발생: " + e.getMessage());
                }
            });
        }


        // ExecutorService 종료 시작: 새로운 작업은 받지 않고, 이미 제출된 작업은 완료될 때까지 실행
        executorService.shutdown();

        try {
            // 모든 작업이 완료될 때까지 대기 (최대 1분)
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                System.err.println("오류: 스레드 작업이 시간 내에 완료되지 않았습니다. 강제 종료 시도.");
                executorService.shutdownNow(); // 완료되지 않은 작업들을 강제로 중단시키려고 시도
                if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                    System.err.println("오류: 강제 종료 후에도 스레드 풀이 완전히 종료되지 않았습니다.");
                }
            }
        } catch (InterruptedException e) {
            System.err.println("오류: 메인 스레드가 작업 완료 대기 중 인터럽트되었습니다. 강제 종료 시도.");
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("\n==================== 테스트 결과 ====================");
        System.out.println("실제로 생성 및 수집된 ID 총 개수 (List size): " + generatedIds.size());

        Set<Long> uniqueIds = new HashSet<>(generatedIds);
        System.out.println("중복 제거 후 유니크한 ID 개수 (Set size): " + uniqueIds.size());

        if (generatedIds.size() != uniqueIds.size()) {
            long numberOfDuplicatesOccurred = generatedIds.size() - uniqueIds.size();
            System.out.println("경고: ID 중복 발생! 총 " + numberOfDuplicatesOccurred + "개의 중복된 ID 인스턴스가 감지되었습니다.");

        } else if (generatedIds.size() == totalExpectedIds) {
            System.out.println("성공: ID 중복이 발생하지 않았습니다. 모든 ID가 유니크합니다.");
        }
        System.out.println("================================================");
    }
}