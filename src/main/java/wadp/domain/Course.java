
package wadp.domain;
import java.util.ArrayList;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Course extends AbstractPersistable<Long> {
    
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<GradeLevel> gradeLevels;
    
    public Course(){
        this.gradeLevels=new ArrayList<GradeLevel>();
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public List<GradeLevel> getGradeLevels() {
        return this.gradeLevels;
                }

    public void setGradeLevels(List<GradeLevel> levels) {
        this.gradeLevels=levels;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
