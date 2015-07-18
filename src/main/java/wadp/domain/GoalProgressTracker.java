package wadp.domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class GoalProgressTracker extends AbstractPersistable<Long> {

    private Status ready;
    @ElementCollection
    @CollectionTable(name = "skill_statuses")
    private Map<Skill, Status> skills;
    
    @ElementCollection
    @CollectionTable(name = "skill_comments")
    private Map<Skill, Comment> comments;

    public GoalProgressTracker() {
        this.ready = Status.UNCONFIRMED;
        this.skills = new HashMap<Skill, Status>();
        this.comments = new HashMap<Skill, Comment>();
    }

    public GoalProgressTracker(Goal goal) {
        this.ready = Status.UNCONFIRMED;
        skills = new HashMap<Skill, Status>();
        for (Skill skill : goal.getSkills()) {
            skills.put(skill, Status.UNCONFIRMED);
        }
    }

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

    private void checkStatus() {
        boolean allStudentConfirmed = true;
        boolean allTeacherConfirmed = true;
        for (Status status : skills.values()) {
            if(allStudentConfirmed){
                if(status != Status.STUDENT_CONFIRMED && status != Status.TEACHER_CONFIRMED){
                    allStudentConfirmed = false;
                }
            }
            if(allTeacherConfirmed){
                if(status != Status.TEACHER_CONFIRMED){
                    allTeacherConfirmed = false;
                }
            }
        }
        if(allTeacherConfirmed){
            this.ready = Status.TEACHER_CONFIRMED;
        } else if( allStudentConfirmed){
            this.ready = Status.STUDENT_CONFIRMED;
        } else {
            this.ready = Status.UNCONFIRMED;
        }
    }

    public Map<Skill, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Skill, Comment> comments) {
        this.comments = comments;
    }

}
