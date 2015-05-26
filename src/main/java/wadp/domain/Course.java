
package wadp.domain;
import java.util.ArrayList;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Course extends AbstractPersistable<Long> {
    
    private String name;
    private String description;
    @OneToMany
    private List<Goal> goals;
    
    public Course(){
        this.goals=new ArrayList<Goal>();
    }
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
