
package wadp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.User;
import wadp.repository.CourseProgressRepository;
import wadp.repository.CourseRepository;
import wadp.repository.GradeLevelRepository;

@Service
public class CourseService {
    
    @Autowired
    private CourseProgressRepository progressRepository;
    
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GradeLevelRepository gradeLevelRepository;
    
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @Transactional
    public void addCourse(Course course) {
        courseRepository.save(course);
    }
    
    public Course getCourseById(Long id){
        return courseRepository.findOne(id);
    }

    public void sortCourseGrades(Course course) {
        Collections.sort(course.getGradeLevels());
    }
    
    @Transactional
    public void updateCourse(Course course, Long courseId){
        Course courseToBeUpdated = courseRepository.findOne(courseId);
        courseToBeUpdated.setName(course.getName());
        courseToBeUpdated.setDescription(course.getDescription());
        
        Long OldGradeLevel1Id = courseToBeUpdated.getGradeLevels().get(0).getId();
        Long OldGradeLevel2Id = courseToBeUpdated.getGradeLevels().get(1).getId();
        Long OldGradeLevel3Id = courseToBeUpdated.getGradeLevels().get(2).getId();
       
        courseToBeUpdated.setGradeLevels(course.getGradeLevels());
      
       

        gradeLevelRepository.delete(OldGradeLevel1Id);
        gradeLevelRepository.delete(OldGradeLevel2Id);
        gradeLevelRepository.delete(OldGradeLevel3Id);
    }
    
        public List<Course> getUsersCourses(User user){
        List<CourseProgressTracker> trackers = progressRepository.findByUser(user);
        List<Course> courses = new ArrayList<Course>();
        
        for (CourseProgressTracker tracker : trackers){
            courses.add(tracker.getCourse());
            
        }
        
        return courses;
        
      }
        
        
}
