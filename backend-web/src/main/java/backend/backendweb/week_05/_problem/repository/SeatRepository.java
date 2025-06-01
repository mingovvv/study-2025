package backend.backendweb.week_05._problem.repository;

import backend.backendweb.week_05._problem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
