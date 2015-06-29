
package wadp.service;

import java.util.Collections;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Course;
import wadp.repository.CourseRepository;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
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
    
}
