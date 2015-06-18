package wadp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.Skill;
import wadp.domain.User;
import wadp.repository.CourseProgressRepository;

@Service
public class ProgressService {

    @Autowired
    private CourseProgressRepository progressRepository;

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

}
