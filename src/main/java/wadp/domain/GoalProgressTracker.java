package wadp.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class GoalProgressTracker extends AbstractPersistable<Long> {

    private boolean ready;
    private HashMap<Skill, Boolean> skills;

    public GoalProgressTracker(Goal goal) {
        this.ready = false;
        skills = new HashMap<Skill, Boolean>();
        for (Skill skill : goal.getSkills()) {
            skills.put(skill, false);
        }
    }

    public boolean updateSkillStatus(Skill skill, boolean status) {
        if (skills.get(skill) != null) {
            skills.put(skill, status);
            return true;
        }
        return false;
    }


    public boolean getStatus() {
        return ready;
    }

    public void setStatus(boolean status) {
        this.ready = status;
    }

    public HashMap<Skill, Boolean> getSkills() {
        return skills;
    }

    public void setSkills(HashMap<Skill, Boolean> skills) {
        this.skills = skills;
    }

}
