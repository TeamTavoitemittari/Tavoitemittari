
package wadp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.User;
import wadp.repository.CourseProgressRepository;

@Service
public class ProgressService {
  
    @Autowired
    private CourseProgressRepository progressRepository;
    
    public CourseProgressTracker getProgress(User user, Course course){
        return progressRepository.findByUserEmailAndCourse(user.getEmail(), course).get(0);
    }
    
}
