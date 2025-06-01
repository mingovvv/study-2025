package backend.backendweb.week_05._problem.controller;

import backend.backendweb.week_05._problem.entity.Seat;
import backend.backendweb.week_05._problem.repository.SeatRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ReservationControllerConcurrencyTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SeatRepository seatRepository;

    private Long seatId;

    @BeforeEach
    void setUp() {
        // 테스트 시작 전, 좌석 하나 생성 (eventName="Concert A", available=true)
        Seat seat = new Seat();
        seat.setEventName("Concert A");
        seat.setAvailable(true);
        seat = seatRepository.save(seat);
        seatId = seat.getId();
    }

    /**
     * 많은 쓰레드가 동시에 예약 요청을 보내면, AS-IS 상태에서는
     * 두 명 이상이 같은 좌석 예약에 성공하는(이중 판매) 현상이 발생하는지 확인한다.
     */
    @Test
    void testConcurrentReservationWithoutLocking() throws InterruptedException {
        int threadCount = 50;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        CountDownLatch readyLatch = new CountDownLatch(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(threadCount);

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failureCount = new AtomicInteger(0);

        String url = "/api/seats/" + seatId + "/reserve";

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    readyLatch.countDown();
                    startLatch.await();

                    // POST 요청 보내기
                    ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

                    // 응답 코드가 200 OK면 성공, 아니라면 실패
                    if (response.getStatusCode().is2xxSuccessful()) {
                        successCount.incrementAndGet();
                    } else {
                        failureCount.incrementAndGet();
                    }

                } catch (InterruptedException ignored) {

                } finally {
                    // 작업 완료
                    doneLatch.countDown();
                }

            });
        }

        // 모든 쓰레드가 준비될 때까지 대기
        readyLatch.await();
        // 동시에 시작 신호
        startLatch.countDown();
        // 모든 쓰레드가 끝날 때까지 대기
        doneLatch.await();

        executor.shutdown();

        System.out.println("성공 횟수: " + successCount.get());
        System.out.println("실패 횟수: " + failureCount.get());

        // AS-IS 상태라면, 성공 횟수가 1 이상이어야 하지만, 동시성 문제로 여러 번 성공할 수 있다.
        // TO-BE 상태라면, 성공 횟수가 1 무조건 1
        Assertions.assertThat(successCount.get()).isEqualTo(1);
    }
}