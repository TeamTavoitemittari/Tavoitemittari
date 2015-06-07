
package wadp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.domain.Course;
import wadp.domain.User;
import wadp.service.CourseService;
import wadp.service.UserService;

@RequestMapping("/course")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private UserService userService;
    
    
    @RequestMapping(method = RequestMethod.POST)
    public void createCourse(){
        //TODO: code for creation of new course
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getCourse(Model model, @PathVariable Long id){
        Course course = courseService.getCourseById(id);
        if(course==null){
            // TODO: throw new course not found exception
        }
        model.addAttribute("course", course);
        User user = userService.getAuthenticatedUser();
        //still need to add: finding course progress per user and adding 
    }
}
