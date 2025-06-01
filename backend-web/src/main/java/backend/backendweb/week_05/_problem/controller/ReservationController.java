package backend.backendweb.week_05._problem.controller;

import backend.backendweb.week_05._problem.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{id}/reserve")
    public ResponseEntity<String> reserve(@PathVariable("id") Long seatId) {
        reservationService.reserveSeat(seatId);
        return ResponseEntity.ok("좌석 " + seatId + " 예약 성공");
    }

}
