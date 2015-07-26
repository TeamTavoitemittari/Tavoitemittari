
package wadp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.GradeProgressTracker;
import wadp.domain.User;


public interface GradeProgressRepository extends JpaRepository<GradeProgressTracker, Long>{
    
    public List<GradeProgressTracker> findByCourse(Course course);
    
    public List<GradeProgressTracker> findByUserAndCourse(User user, Course course);
    
}
