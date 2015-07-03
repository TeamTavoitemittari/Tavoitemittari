
package wadp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Comment extends AbstractPersistable<Long>{
    
    @Lob
    @Column(length=10000, name="comment")
    private String comment;
    
    @OneToOne
    private Skill skill;
    
    public Comment(){
    }
    
    public Comment(Skill skill){
        this.skill=skill;
        this.comment="";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    
    
}
