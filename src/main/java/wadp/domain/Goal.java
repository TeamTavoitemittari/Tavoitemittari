

package wadp.domain;
import java.util.ArrayList;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Goal extends AbstractPersistable<Long> implements Comparable<Goal>{
    @NotNull(message="Tavoitteen nimeä ei voi jättää tyhjäksi.")
    @NotBlank(message="Tavoitteen nimeä ei voi jättää tyhjäksi.")
    @Length(max=255, message="Tavoitteen nimi korkeintaan 255 kirjainta.")
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @Valid
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
    
    @Override
    public int compareTo(Goal o) {
        return getId().compareTo(o.getId());
    }
    
}    
        
   

        
