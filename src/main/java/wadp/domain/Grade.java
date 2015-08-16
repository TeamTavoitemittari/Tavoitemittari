
package wadp.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Grade extends AbstractPersistable<Long> {

    @ManyToOne
    private User user;

    private String courseName;

    private Long courseId;

    private String grade;

    public User getUser() {
        return user;
    }
    
    public Grade(){

    }
    public void setUser(User user) {
        this.user = user;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName=courseName;
    }
    
    public Long getCourseId(){
        return courseId;
    }

    public void setCourseId(Long courseId){
        this.courseId=courseId;
    }

    public String getGrade(){
        return grade;
    }

    public void setGrade(String grade){
        this.grade=grade;
    }

}
