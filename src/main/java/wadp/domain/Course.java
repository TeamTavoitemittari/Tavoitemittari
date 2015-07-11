
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
public class Course extends AbstractPersistable<Long> {
    
    @NotNull(message="Kurssin nimeä ei voi jättää tyhjäksi.")
    @NotBlank(message="Kurssin nimeä ei voi jättää tyhjäksi.")
    @Length(max=255, message="Kurssin nimi korkeintaan 255 kirjainta.")
    private String name;
    @NotNull(message="Kurssin kuvausta ei voi jättää tyhjäksi.")
    @NotBlank(message="Kurssin kuvausta ei voi jättää tyhjäksi.")
    @Length(max=255, message="Kurssin kuvaus korkeintaan 255 kirjainta.")
    private String description;
    
    @Valid
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
