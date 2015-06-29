
package wadp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.repository.CommentRepository;
import wadp.repository.CourseProgressRepository;
import wadp.repository.CourseRepository;
import wadp.repository.ExerciseRepository;
import wadp.repository.GoalProgressRepository;
import wadp.repository.GoalRepository;
import wadp.repository.GradeLevelRepository;
import wadp.repository.GradeProgressRepository;
import wadp.repository.SkillRepository;
import wadp.repository.UserRepository;

@Service
public class DeletionService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CourseProgressRepository courseProgressRepository;
    
    @Autowired
    private GoalProgressRepository goalProgressRepository;
    
    @Autowired
    private GradeProgressRepository gradeProgressRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private GradeLevelRepository gradeLevelRepository;
    
    @Autowired
    private GoalRepository goalRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private ExerciseRepository exerciseRepository;
    
    
    public void deleteEverything(){
        userRepository.deleteAll();
        courseProgressRepository.deleteAll();
        goalProgressRepository.deleteAll();
        gradeProgressRepository.deleteAll();
        commentRepository.deleteAll();
        courseRepository.deleteAll();
        gradeLevelRepository.deleteAll();
        goalRepository.deleteAll();
        skillRepository.deleteAll();
        exerciseRepository.deleteAll();
        
    }
    
}
