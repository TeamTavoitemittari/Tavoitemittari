package wadp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.GoalProgressTracker;
import wadp.domain.GradeProgressTracker;
import wadp.domain.Skill;
import wadp.domain.User;
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

    public void updateSkillStatus(CourseProgressTracker progressTracker, Skill skill, boolean status) {
        progressTracker.updateSkillStatus(skill, status);
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

}
