
package wadp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Exercise extends AbstractPersistable<Long>{
    
    ///Based on logic that one excercise object per each goal rather than individual object for
    ///every single excercise of each goal. Can be changed later.
    @Length(max=2000, message="Harjoituksiin korkeintaan 2000 kirjainta.")
    @Column(length = 2000)
    private String description;

    public Exercise() {
    }
    
    public Exercise(String description){
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString(){
        return this.description;
    }
    
}
