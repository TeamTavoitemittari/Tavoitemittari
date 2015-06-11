
package wadp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.User;
import wadp.service.CourseService;
import wadp.service.ProgressService;
import wadp.service.UserService;

@Controller
@RequestMapping(value = "/course")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProgressService progressService;
    
    
    @RequestMapping(method = RequestMethod.POST)
    public void createCourse(){
        //TODO: code for creation of new course
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCourse(Model model, @PathVariable Long id){
        Course course = courseService.getCourseById(id);
        
        if(course==null){
            return "redirect:/mycourses";
        }
        model.addAttribute("course", course);
        User user = userService.getAuthenticatedUser();
        CourseProgressTracker tracker = progressService.getProgress(user, course);
        model.addAttribute("tracker", tracker);
        
        return "course";
    }
}
