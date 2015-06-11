
package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.GradeLevel;


public interface GradeLevelRepository extends JpaRepository<GradeLevel, Long>{
    
}
