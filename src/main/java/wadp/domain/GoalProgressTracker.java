
package wadp.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class GoalProgressTracker extends AbstractPersistable<Long>{
    
    private Status status;
    private HashMap<Skill, Status> skills;
    
    public GoalProgressTracker(Goal goal){
        this.status=Status.UNREADY;
        skills=new HashMap<Skill, Status>();
        for (Skill skill : goal.getSkills()) {
            skills.put(skill, Status.UNREADY);
        }
    }
    
    public void setSkillReady(Skill skill){
        skills.put(skill, Status.READY);
        boolean allReady = true;
        for(Status goalStatus : skills.values()){
            if(goalStatus == Status.UNREADY){
                allReady = false;
                break;
            }
        }
        if(allReady) this.status=Status.READY;
    }
    
    public void setSkillUnready(Skill skill){
        skills.put(skill, Status.UNREADY);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public HashMap<Skill, Status> getSkills() {
        return skills;
    }

    public void setSkills(HashMap<Skill, Status> skills) {
        this.skills = skills;
    }
    
    
    
}
