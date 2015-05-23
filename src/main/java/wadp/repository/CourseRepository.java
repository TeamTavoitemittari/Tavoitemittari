

package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Course;


public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
