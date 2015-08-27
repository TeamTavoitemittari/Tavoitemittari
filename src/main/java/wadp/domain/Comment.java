
package wadp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Represents comments made on a skill.
 * Comments can be made by either the student to which the skill is related or the teacher of the course.
 */
@Entity
public class Comment extends AbstractPersistable<Long>{
    
    @Column (length = 5000)
    private String comment;
    
    @OneToOne
    private User user;
    @OneToOne
    private Course course;
    
    @OneToOne
    private Skill skill;
    
    public Comment(){
    }

    /**
     * Creates an empty comment object when the user signs up for the course.
     * @param skill to which the comment is related
     * @param user student whose skill is commented on
     * @param course course to which the comment is related
     */
      public Comment(Skill skill, User user, Course course){
        this.user = user;
        this.course = course;
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
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
}
