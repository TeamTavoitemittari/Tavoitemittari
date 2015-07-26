
package wadp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Course;
import wadp.domain.GoalProgressTracker;
import wadp.domain.User;

public interface GoalProgressRepository extends JpaRepository<GoalProgressTracker, Long>{
    
    public List<GoalProgressTracker> findByCourse(Course course);
    public List<GoalProgressTracker> findByUserAndCourse(User user, Course course);  
}
