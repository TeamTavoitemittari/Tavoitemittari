
package wadp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Skill;
import wadp.repository.SkillRepository;

@Service
public class SkillService {
    
    @Autowired
    private SkillRepository skillRepository;
    
    public List<Skill> getSkills(){
        return skillRepository.findAll();
    }
    
    @Transactional
    public void addSkill(Skill skill){
        skillRepository.save(skill);
    }
    
    public Skill findSkill(Long skillId){
        return skillRepository.findOne(skillId);
    }
    
}
