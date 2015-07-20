
package wadp.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class GradeProgressTracker extends AbstractPersistable<Long> {
    
    private boolean ready;
    
    @OneToOne
    private User user;
    @OneToOne
    private Course course;
    
    @ElementCollection
    @CollectionTable(name = "goal_progress_trackers")
    private Map<Goal, GoalProgressTracker> goals;
    
    public GradeProgressTracker(){
        this.goals=new HashMap<Goal, GoalProgressTracker>();
    }
    
       public GradeProgressTracker(GradeLevel level, User user, Course course){
        this.user = user;
        this.course = course;
        this.ready=false;
        this.goals=new HashMap<Goal, GoalProgressTracker>();
        for (Goal goal : level.getGoals()) {
            goals.put(goal, new GoalProgressTracker(goal, user, course));
        }
    }

    
    public boolean updateSkillStatus(Skill skill, Status status){
        for (GoalProgressTracker tracker : goals.values()) {
            boolean foundInGoal = tracker.updateSkillStatus(skill, status);
            if(foundInGoal) return true;
        }
        return false;
    }

    public boolean isReady() {
        return ready;
    }
    
    public boolean getReady(){
        return this.ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

   public Map<Goal, GoalProgressTracker> getGoals() {
        
      try {
           TreeMap<Goal, GoalProgressTracker> sortMap = new TreeMap<>(goals);
           return sortMap;
       } catch (NullPointerException e) {
            return goals;
        }
    }

    public void setGoals(HashMap<Goal, GoalProgressTracker> goals) {
        this.goals = goals;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    
    
}
