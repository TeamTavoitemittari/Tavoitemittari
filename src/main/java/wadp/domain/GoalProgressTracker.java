package wadp.domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class GoalProgressTracker extends AbstractPersistable<Long> {

    private boolean ready;
    @ElementCollection
    @CollectionTable(name = "skill_statuses")
    private Map<Skill, Boolean> skills;

    public GoalProgressTracker() {
        skills = new HashMap<Skill, Boolean>();
    }

    public GoalProgressTracker(Goal goal) {
        this.ready = false;
        skills = new HashMap<Skill, Boolean>();
        for (Skill skill : goal.getSkills()) {
            skills.put(skill, false);
        }
    }

    public boolean updateSkillStatus(Skill skill, boolean ready) {

        if (skills.get(skill) != null) {
            skills.put(skill, ready);
            if (ready) {
                checkIfReady();
            } else if (this.ready) {
                this.ready=false;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean getReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public Map<Skill, Boolean> getSkills() {
        return skills;
    }

    public void setSkills(HashMap<Skill, Boolean> skills) {
        this.skills = skills;
    }

    private void checkIfReady() {
        boolean allReady = true;
        for (boolean skillReady : skills.values()) {
            if (!skillReady) {
                allReady = false;
            }
            break;
        }
        this.ready = allReady;
    }

}
