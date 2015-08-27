
package wadp.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;


/**
 * Represents the collection of goals which need to be completed in order
 * for a student to reach a specific grade level of a course. Usually bundled
 * to grade levels: 5-6, 7-8, 9-10
 */
@Entity
public class GradeLevel extends AbstractPersistable<Long> implements Comparable<GradeLevel> {

    // The grade to which the goals amount (f.e. 5-6)
    private String grade;
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    @NotEmpty(message="Tavoite ei saa olla tyhjä.")
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

    @Override
    public int compareTo(GradeLevel o) {
        return  o.getGrade().compareTo(getGrade());
    }
}
