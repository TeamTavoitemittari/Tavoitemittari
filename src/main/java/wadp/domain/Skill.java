

package wadp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Represents a specific skill that the student should attain.
 * A set of skills are a goal.
 */
@Entity
public class Skill extends AbstractPersistable<Long>{
    @Length(max=255, message="Taitojen kuvauksiin korkeintaan 255 kirjainta.")
    private String description;
    
    //Excercises with wich the student practices this skill.
    @Length(max=2000, message="Harjoituksiin korkeintaan 2000 kirjainta.")
    @Column(length = 2000)
    private String exercise;

    public Skill() {
    }

    public Skill(String description, String exercise) {
        this.description = description;
        this.exercise = exercise;
    }
    

    
  

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
    


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
