package wadp.domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class CourseProgressTracker extends AbstractPersistable<Long> {

    @OneToOne
    private User user;
    @OneToOne
    private Course course;
    @ElementCollection
    @CollectionTable(name = "grade_level_progress")
    private Map<GradeLevel, GradeProgressTracker> gradeLevels;

    public CourseProgressTracker(){
        this.gradeLevels=new HashMap<GradeLevel, GradeProgressTracker>();
    }
    
    public CourseProgressTracker(User user, Course course) {
        this.user = user;
        this.course = course;
        this.gradeLevels = new HashMap<GradeLevel, GradeProgressTracker>();
        for (GradeLevel gradeLevel : course.getGradeLevels()) {
            gradeLevels.put(gradeLevel, new GradeProgressTracker(gradeLevel));
        }
    }

    public boolean updateSkillStatus(Skill skill, boolean status) {
        for (GradeProgressTracker tracker : gradeLevels.values()){
            boolean found = tracker.updateSkillStatus(skill, status);
            if(found) return true;
        }
        return false;
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

    public Map<GradeLevel, GradeProgressTracker> getGradeLevels() {
        return gradeLevels;
    }

    public void setGradeLevels(HashMap<GradeLevel, GradeProgressTracker> gradeLevels) {
        this.gradeLevels = gradeLevels;
    }

}
