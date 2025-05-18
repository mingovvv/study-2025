package backend.backendweb.week_03._problem.repository;

import backend.backendweb.week_03._problem.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
