package wadp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.User;

public interface CourseProgressRepository extends JpaRepository<CourseProgressTracker, Long> {

    public List<CourseProgressTracker> findByUserAndCourse(User user, Course course);

    public List<CourseProgressTracker> findByCourse(Course course);
    
    public List<CourseProgressTracker> findByUser(User user);
    
    public List<CourseProgressTracker> findByUserAndCourse_Teacher(User user, User teacher);
}
