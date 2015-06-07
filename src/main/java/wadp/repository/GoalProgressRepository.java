
package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.GoalProgressTracker;

public interface GoalProgressRepository extends JpaRepository<GoalProgressTracker, Long>{
    
}
