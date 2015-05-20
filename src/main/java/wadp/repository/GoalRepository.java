
package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

}
