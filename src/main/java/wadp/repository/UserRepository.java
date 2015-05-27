
package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.User;

/**
 *
 * @author OP
 */

public interface UserRepository extends JpaRepository <User, Long> {
    
  /// no  username so users identified by email address
        
    User findByEmail(String email);
    
}
