

package wadp.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Skill extends AbstractPersistable<Long>{
    @Length(max=255, message="Taitojen kuvauksiin korkeintaan 255 kirjainta.")
    private String description;
    
    @OneToMany(cascade = CascadeType.ALL)
    @Valid
    private List<Exercise> exercises;
    
    public Skill(){
        this.exercises=new ArrayList<Exercise>();
    }
    
    public Skill(String description){
        this.description=description;
        this.exercises=new ArrayList<Exercise>();
    }
    
    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }
    
    public List<Exercise> getExercises(){
        return this.exercises;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
