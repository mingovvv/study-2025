package backend.backendweb.week_03._problem.repository;

import backend.backendweb.week_03._problem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
