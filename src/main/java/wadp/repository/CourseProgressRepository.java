
package wadp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;

public interface CourseProgressRepository extends JpaRepository<CourseProgressTracker, Long>{
    public List<CourseProgressTracker> findByUserEmailAndCourse(String userEmail, Course course);
}
