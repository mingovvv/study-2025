package backend.backendweb.week_05._problem.service;

import backend.backendweb.week_05._problem.entity.Seat;
import backend.backendweb.week_05._problem.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {

    private final SeatRepository seatRepository;

    /**
     * AS-IS: 락 없이 단순 조회→검증→저장
     */
    @Transactional
    public void reserveSeat(Long seatId) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 좌석: " + seatId));

        if (!seat.isAvailable()) {
            throw new IllegalStateException("이미 예약된 좌석입니다: " + seatId);
        }

        seat.setAvailable(false);

    }

}
