
package wadp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Comment;
import wadp.domain.Course;
import wadp.domain.Skill;
import wadp.domain.User;


public interface CommentRepository extends JpaRepository<Comment, Long>{
    
     public List<Comment> findByCourse(Course course);
     
     public List<Comment> findByUserAndCourse(User user, Course course);
}
