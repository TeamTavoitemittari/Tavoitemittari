
package wadp.domain;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Course extends AbstractPersistable<Long> {
    
    private String name;
    
    @OneToMany
    private List<Goal> goals;
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public List<Goal> getGoals() {
        return this.goals;
                }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
