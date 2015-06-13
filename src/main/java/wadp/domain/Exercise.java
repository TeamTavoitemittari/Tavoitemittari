
package wadp.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Exercise extends AbstractPersistable<Long>{
    
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
