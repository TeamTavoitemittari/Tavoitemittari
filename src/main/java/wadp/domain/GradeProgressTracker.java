
package wadp.domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class GradeProgressTracker extends AbstractPersistable<Long> {
    
    private boolean ready;
    
    @ElementCollection
    @CollectionTable(name = "goal_progress_trackers")
    private Map<Goal, GoalProgressTracker> goals;
    
    public GradeProgressTracker(){
        this.goals=new HashMap<Goal, GoalProgressTracker>();
    }
    
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
    
    public boolean getReadt(){
        return this.ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public Map<Goal, GoalProgressTracker> getGoals() {
        return goals;
    }

    public void setGoals(HashMap<Goal, GoalProgressTracker> goals) {
        this.goals = goals;
    }
    
    
    
}
