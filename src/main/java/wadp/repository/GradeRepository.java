
package wadp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Grade;
import wadp.domain.User;


public interface GradeRepository extends JpaRepository<Grade, Long>{
    
         public List<Grade> findByUser(User user);
         public List<Grade> findByCourseId(Long courseId);
         public Grade findByUserAndCourseId(User user, Long courseId);
}
