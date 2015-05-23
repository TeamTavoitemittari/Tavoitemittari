

package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Exercise;


public interface ExerciseRepository extends JpaRepository<Exercise, Long>{
    
}
