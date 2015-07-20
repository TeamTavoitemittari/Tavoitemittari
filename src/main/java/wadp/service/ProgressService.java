package wadp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.*;
import wadp.repository.CourseProgressRepository;
import wadp.repository.GoalProgressRepository;
import wadp.repository.GradeLevelRepository;
import wadp.repository.GradeProgressRepository;

@Service
public class ProgressService {

    @Autowired
    private CourseProgressRepository progressRepository;
    
    @Autowired
    private GradeProgressRepository gradeRepository;
    
    @Autowired
    private GoalProgressRepository goalRepository;

    public CourseProgressTracker getProgress(User user, Course course) {
        List<CourseProgressTracker> trackers = progressRepository.findByUserAndCourse(user, course);
        if (trackers.isEmpty()) {
            return null;
        }
        return progressRepository.findByUserAndCourse(user, course).get(0);
    }

    @Transactional
    public void updateSkillStatus(CourseProgressTracker progressTracker, Skill skill, Status status) {
        progressTracker.updateSkillStatus(skill, status);
    }

    @Transactional
    public void swapSkillsStatus(CourseProgressTracker progressTracker, GradeLevel gradeLevel, Goal goal, Skill skill) {
        GoalProgressTracker gTracker = progressTracker.getGradeLevels().get(gradeLevel).getGoals().get(goal);
        Status status = gTracker.getSkills().get(skill);

        if (status==Status.STUDENT_CONFIRMED) {
            gTracker.updateSkillStatus(skill, Status.UNCONFIRMED);
        } else if(status==Status.UNCONFIRMED) {
            gTracker.updateSkillStatus(skill, Status.STUDENT_CONFIRMED);
        }
    }

    @Transactional
    public void swapSkillStatusAsTeacher(CourseProgressTracker progressTracker, GradeLevel gradeLevel, Goal goal, Skill skill) {
        GoalProgressTracker gTracker = progressTracker.getGradeLevels().get(gradeLevel).getGoals().get(goal);
        Status status = gTracker.getSkills().get(skill);

        if (status==Status.TEACHER_CONFIRMED) {
            gTracker.updateSkillStatus(skill, Status.UNCONFIRMED);
        } else {
            gTracker.updateSkillStatus(skill, Status.TEACHER_CONFIRMED);
        }
    }
    
    @Transactional
    public CourseProgressTracker createProgressTracker(User user, Course course){
        CourseProgressTracker tracker = new CourseProgressTracker(user, course);
        progressRepository.save(tracker);
        return tracker;
    }
    
    @Transactional
    public void saveCourseTracker(CourseProgressTracker tracker){
        progressRepository.save(tracker);
    }
    
    @Transactional
    public void saveGradeTracker(GradeProgressTracker tracker){
        gradeRepository.save(tracker);
    }
    
    @Transactional
    public void saveGoalTracker(GoalProgressTracker tracker){
        goalRepository.save(tracker);
    }

    public List<CourseProgressTracker> getProgressByCourse(Course course){
        List<CourseProgressTracker> trackers = progressRepository.findByCourse(course);
        return trackers;
    }
    
   @Transactional
   public void deleteCourseProgressTrackers(List<CourseProgressTracker> trackers){
        progressRepository.delete(trackers);
    }
    
    @Transactional
    public void deleteGradeProgressTrackers(List<GradeProgressTracker> trackers){
        gradeRepository.delete(trackers);
    }
    
    @Transactional
    public void deleteGoalProgressTrackers(List<GoalProgressTracker> trackers){
        goalRepository.delete(trackers);
    }

    public List<CourseProgressTracker> getCourseProgressTrackersByCourse(Course course){
        List<CourseProgressTracker> trackers = progressRepository.findByCourse(course);
        return trackers;
    }
    public List<GradeProgressTracker> getGradeProgressTrackersByCourse(Course course){
        List<GradeProgressTracker> trackers = gradeRepository.findByCourse(course);
        return trackers;
    }
    
     public List<GoalProgressTracker> getGoalProgressTrackersByCourse(Course course){
        List<GoalProgressTracker> trackers = goalRepository.findByCourse(course);
        return trackers;
    }

}
