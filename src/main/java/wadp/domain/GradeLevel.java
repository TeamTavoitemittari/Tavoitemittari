
package wadp.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
public class GradeLevel extends AbstractPersistable<Long> implements Comparable<GradeLevel> {
    
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
