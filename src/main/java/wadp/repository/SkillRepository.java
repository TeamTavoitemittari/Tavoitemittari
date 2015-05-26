

package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Skill;

/**
 *
 * @author villvirt
 */
public interface SkillRepository extends JpaRepository<Skill, Long>{
    
}
