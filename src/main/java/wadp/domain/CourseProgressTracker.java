
package wadp.domain;

import java.util.HashMap;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class CourseProgressTracker extends AbstractPersistable<Long>{
    
    private String userEmail;
    private Course course;
    private HashMap<Goal, GoalProgressTracker> goals;
    
    
    public CourseProgressTracker(User user, Course course){
        userEmail = user.getEmail();
        goals = new HashMap<Goal, GoalProgressTracker>();
        for (Goal goal : course.getGoals()) {
            goals.put(goal, new GoalProgressTracker(goal));
        }
    }

    public String getEmail() {
        return userEmail;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }
    

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public HashMap<Goal, GoalProgressTracker> getGoals() {
        return goals;
    }

    public void setGoals(HashMap<Goal, GoalProgressTracker> goals) {
        this.goals = goals;
    }
    
}
