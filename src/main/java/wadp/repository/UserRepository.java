
package wadp.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {
    
  /// no  username so users identified by email address
        
    User findByEmail(String email);
    List<User> findByUserRole(String userRole, Sort sort);
    List<User> findByUserRole(String userRole);
}
