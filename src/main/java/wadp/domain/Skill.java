

package wadp.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Skill extends AbstractPersistable<Long>{
    
    private String description;
    
    @OneToMany
    private List<Exercise> exercises;

    public Skill() {
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
