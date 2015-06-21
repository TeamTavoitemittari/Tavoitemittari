

package wadp.domain;
import java.util.ArrayList;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Goal extends AbstractPersistable<Long>{
    
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Skill> skills;
    
    public Goal(){
        this.skills= new ArrayList<Skill>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
        
     public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}    
        
   

        
