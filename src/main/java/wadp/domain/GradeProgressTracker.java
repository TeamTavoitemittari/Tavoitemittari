
package wadp.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class GradeProgressTracker extends AbstractPersistable<Long> {
    
    private boolean ready;
    private HashMap<Goal, GoalProgressTracker> goals;
    
    public GradeProgressTracker(GradeLevel level){
        this.ready=false;
        this.goals=new HashMap<Goal, GoalProgressTracker>();
        for (Goal goal : level.getGoals()) {
            goals.put(goal, new GoalProgressTracker(goal));
        }
    }
    
    public boolean updateSkillStatus(Skill skill, boolean status){
        for (GoalProgressTracker tracker : goals.values()) {
            boolean foundInGoal = tracker.updateSkillStatus(skill, status);
            if(foundInGoal) return true;
        }
        return false;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public HashMap<Goal, GoalProgressTracker> getGoals() {
        return goals;
    }

    public void setGoals(HashMap<Goal, GoalProgressTracker> goals) {
        this.goals = goals;
    }
    
    
    
}
