package wadp.domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * Contains information on the students progress on one goal of a grade level.
 *
 */
@Entity
public class GoalProgressTracker extends AbstractPersistable<Long> {

    // Status of completeness for this goal (not ready, student confirmed, teacher confirmed)
    private Status ready;
    
    @OneToOne
    private User user;
    @OneToOne
    private Course course;

    //Status of completeness for specific skills within the goal
    @ElementCollection
    @CollectionTable(name = "skill_statuses")
    private Map<Skill, Status> skills;

    //Comments related to a specific user and skill
    @ElementCollection
    @CollectionTable(name = "skill_comments")
    private Map<Skill, Comment> comments;

    public GoalProgressTracker() {
        this.ready = Status.UNCONFIRMED;
        this.skills = new HashMap<Skill, Status>();
        this.comments = new HashMap<Skill, Comment>();
    }

      public GoalProgressTracker(Goal goal, User user, Course course) {
        this.user = user;
        this.course = course;
        this.ready = Status.UNCONFIRMED;
        skills = new HashMap<Skill, Status>();
        for (Skill skill : goal.getSkills()) {
            skills.put(skill, Status.UNCONFIRMED);
        }
    }

    /**
     * Updates the status of a specific skill
     * @param skill
     * @param status of completeness
     * @return boolean of whether or not the operation was successful
     */
    public boolean updateSkillStatus(Skill skill, Status status) {

        if (skills.get(skill) != null) {
            skills.put(skill, status);
                checkStatus();
            return true;
        } else {
            return false;
        }

    }

    public Status getReady() {
        return ready;
    }

    public void setReady(Status ready) {
        this.ready = ready;
    }

    public Map<Skill, Status> getSkills() {
        return skills;
    }

    public void setSkills(HashMap<Skill, Status> skills) {
        this.skills = skills;
    }

    /**
     * Checks and updates the status of the student's progress in this goal
     */
    private void checkStatus() {
        boolean allStudentConfirmed = checkStudentConfirmations();
        boolean allTeacherConfirmed = checkTeacherConfirmations();
        if(allTeacherConfirmed){
            this.ready = Status.TEACHER_CONFIRMED;
        } else if( allStudentConfirmed){
            this.ready = Status.STUDENT_CONFIRMED;
        } else {
            this.ready = Status.UNCONFIRMED;
        }
    }

    /**
     * Checks if the student or the teacher has confirmed all skills
     * @return boolean
     */
    private boolean checkStudentConfirmations(){
        for(Status status : skills.values()){
            if(status != Status.STUDENT_CONFIRMED && status != Status.TEACHER_CONFIRMED){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the teacher has confirmed all the skills
     * @return boolean
     */
    private boolean checkTeacherConfirmations(){
        for(Status status : skills.values()){
            if(status != Status.TEACHER_CONFIRMED){
                return false;
            }
        }
        return true;
    }

    public Map<Skill, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Skill, Comment> comments) {
        this.comments = comments;
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
