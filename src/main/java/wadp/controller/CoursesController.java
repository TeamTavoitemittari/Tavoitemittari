
package wadp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.domain.Course;
import wadp.service.CourseService;
import wadp.service.UserService;

@Controller
@RequestMapping("/mycourses")
public class CoursesController {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showCoursesPage(Model model) {
        if (userService.getAuthenticatedUser().getUserRole().equals("student")){
         model.addAttribute("courses", courseService.getCoursesInUse());
         model.addAttribute("owncourses", courseService.getUsersCourses(userService.getAuthenticatedUser()));
     
        }
        if (userService.getAuthenticatedUser().getUserRole().equals("teacher")){
         
         List<Course> teachersCourses = courseService.getCoursesInUseByTeacher(userService.getAuthenticatedUser());
         model.addAttribute("teachersCourses", teachersCourses);
         model.addAttribute("counts", courseService.GetStudentCountsForTeachersEachCourseAsMap(userService.getAuthenticatedUser(), teachersCourses));
         
        } 
        return "mycourses";
       
    }
}
