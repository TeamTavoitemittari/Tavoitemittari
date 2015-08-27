package wadp.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Contains the information about a student's progress in a specific course.
 *
 */
@Entity
public class CourseProgressTracker extends AbstractPersistable<Long> {

    @OneToOne
    private User user;
    @OneToOne
    private Course course;

    /**
     * Collection of progress trackers related to each grade level
     * of the course.
     */
    @ElementCollection
    @CollectionTable(name = "grade_level_progress")
    private Map<GradeLevel, GradeProgressTracker> gradeLevels;
    
    private boolean completed;

    public CourseProgressTracker(){
        this.gradeLevels=new HashMap<GradeLevel, GradeProgressTracker>();
    }

    /**
     * Creates a course progress tracker with matching grade level trackers
     * for each grade level of the course.
     * @param user the student
     * @param course the course to which the progress is related
     */
    public CourseProgressTracker(User user, Course course) {
        this.user = user;
        this.course = course;
        this.gradeLevels = new HashMap<GradeLevel, GradeProgressTracker>();
        for (GradeLevel gradeLevel : course.getGradeLevels()) {
            gradeLevels.put(gradeLevel, new GradeProgressTracker(gradeLevel, user, course));
        }
    }


    /**
     * Updates the progress status (not ready, student confirmed, teacher confirmed) for a specific skill.
     * @param skill
     * @param status ready boolean
     * @return whether the operation was successful or not.
     */
    public boolean updateSkillStatus(Skill skill, Status status) {
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

        try {
            TreeMap<GradeLevel, GradeProgressTracker> sortMap = new TreeMap<>(gradeLevels);
            return sortMap;
        } catch (NullPointerException e) {
            return gradeLevels;
        }


    }

    public void setGradeLevels(Map<GradeLevel, GradeProgressTracker> gradeLevels) {
        this.gradeLevels = gradeLevels;
    }
    
    public void setCompleted(Boolean completed){
        this.completed=completed;
    }
    public boolean getCompleted(){
        return completed;
    }

}
