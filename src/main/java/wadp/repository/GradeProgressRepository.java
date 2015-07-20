
package wadp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Course;
import wadp.domain.GradeProgressTracker;


public interface GradeProgressRepository extends JpaRepository<GradeProgressTracker, Long>{
    
    public List<GradeProgressTracker> findByCourse(Course course);
    
}
