
package wadp.domain;
import java.util.ArrayList;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Represents an occurrence of a course. There can be several courses with the same content (f.e. Algebra 1), all
 * have their own course object.
 */
@Entity
public class Course extends AbstractPersistable<Long> {


    @OneToOne
    User teacher;
    
    @NotNull(message="Kurssin nimeä ei voi jättää tyhjäksi.")
    @NotBlank(message="Kurssin nimeä ei voi jättää tyhjäksi.")
    @Length(max=255, message="Kurssin nimi korkeintaan 255 kirjainta.")
    private String name;
    @NotNull(message="Kurssin kuvausta ei voi jättää tyhjäksi.")
    @NotBlank(message="Kurssin kuvausta ei voi jättää tyhjäksi.")
    @Length(max=255, message="Kurssin kuvaus korkeintaan 255 kirjainta.")
    private String description;

    /**
     * The grade levels for this course, usually grades are bundled as 5-6, 7-8 and 9-10.
     */
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    private List<GradeLevel> gradeLevels;
    
    Boolean inUse;


    /**
     * Creates an empty course that is not yet published (in use) to students.
     */
    public Course(){
        this.gradeLevels=new ArrayList<GradeLevel>();
        this.inUse=false;
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

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }
    
    
}
