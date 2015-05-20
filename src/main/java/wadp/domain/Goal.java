/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wadp.domain;
import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
/**
 *
 * @author mnoponen
 */
@Entity
public class Goal extends AbstractPersistable<Long>{
    
    private String grade;
    @OneToMany
    private List<Skill> skills;
    
    
     public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
        
     public List<Skill> getSkills() {
        return this.skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}    
        
   

        
