
package wadp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Course;
import wadp.domain.Grade;
import wadp.domain.User;
import wadp.repository.CourseProgressRepository;
import wadp.repository.GradeRepository;

@Service
public class GradeService {
    
    @Autowired
    GradeRepository gradeRepository;
    
    @Autowired
    CourseProgressRepository courseProgressRepository;
    
    @Transactional
    public void giveGrade(User user, Course course, String grade){
        Grade newGrade = new Grade();
        newGrade.setUser(user);
        newGrade.setCourseId(course.getId());
        newGrade.setCourseName(course.getName());
        newGrade.setGrade(grade);
        gradeRepository.save(newGrade);
        courseProgressRepository.findByUserAndCourse(user, course).get(0).setCompleted(true);
        
    }
    
    public List<Grade> getCourseGrades(Course course){
        return gradeRepository.findByCourseId(course.getId());
    }
    public Grade getStudentCourseGrade(User user, Course course){
        return gradeRepository.findByUserAndCourseId(user, course.getId());
    }
    public List<Grade> getStudentGrades(User user){
        return gradeRepository.findByUser(user);
    }
    
 
    @Transactional
    public void editGrade(User user, Course course, String grade){
        if (grade == null || grade.trim().isEmpty()) {
          Grade editable = gradeRepository.findByUserAndCourseId(user, course.getId());  
          gradeRepository.delete(editable);
          courseProgressRepository.findByUserAndCourse(user, course).get(0).setCompleted(false);
          
        }
        else  {
        Grade editable = gradeRepository.findByUserAndCourseId(user, course.getId());
        editable.setGrade(grade);
        gradeRepository.save(editable);
        }
    }
    
    @Transactional
    public void deleteGrades(List<Grade> grades){
        gradeRepository.delete(grades);
    }
    
}
