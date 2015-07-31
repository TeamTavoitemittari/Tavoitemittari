

package wadp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Course;
import wadp.domain.User;


public interface CourseRepository extends JpaRepository<Course, Long>{
    
    
    public List<Course> findByTeacher(User teacher);
    
    public List<Course> findByTeacherAndInUse(User teacher, Boolean inUse);
    
    public List<Course> findByInUse(Boolean inUse);
    
}
