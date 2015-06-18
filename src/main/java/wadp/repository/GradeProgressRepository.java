
package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.GradeProgressTracker;


public interface GradeProgressRepository extends JpaRepository<GradeProgressTracker, Long>{
    
}
