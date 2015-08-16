

package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
    
}
