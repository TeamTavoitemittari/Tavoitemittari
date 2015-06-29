
package wadp.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Comment extends AbstractPersistable<Long>{
    
    private String comment;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @OneToOne
    private Skill skill;
    
    public Comment(){
    }
    
    public Comment(User user, Skill skill){
        this.user=user;
        this.skill=skill;
        this.comment="";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
    
    
}
