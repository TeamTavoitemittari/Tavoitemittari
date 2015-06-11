
package wadp.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
public class GradeLevel extends AbstractPersistable<Long>{
    
    private String grade;
    @OneToMany
    private List<Goal> goals;

    public String getLevel() {
        return grade;
    }

    public void setLevel(String level) {
        this.grade = level;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    
    
}
