package wadp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wadp.domain.Course;
import wadp.domain.User;
import wadp.service.CourseService;
import wadp.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    

    @Autowired
    private CourseService courseService;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.GET)
    public String getStudents(Model model) {
        model.addAttribute("users", userService.list());
        model.addAttribute("courses", courseService.getCourses());
        return "admin_index";
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("courses", courseService.getUsersCourses(user));
        return "student";
    }
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/{courseId}/{userId}/remove", method = RequestMethod.DELETE)
    public String removeUserFromCourse(RedirectAttributes redirectAttributes, @PathVariable Long courseId, @PathVariable Long userId) {
       courseService.RemoveUserFromCourse(courseId, userId);
       return "redirect:/course/" + courseId + "/goalometer";
    }
    
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/{userId}/delete", method = RequestMethod.DELETE)
     public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable Long userId) {
       userService.deleteUser(userId);
       return "redirect:/admin";
    }
    
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value="/newuser")
    public String createNewuser(){
        return "register";
    }
    
    
    
    
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "filter/{id}", method = RequestMethod.GET)
    public String getFilteredUsers(Model model, @PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        List<User> users = courseService.getCourseStudents(course);
        users.add(courseService.getCourseById(id).getTeacher());
        model.addAttribute("users", users);
        model.addAttribute("courses", courseService.getCourses());
        return "admin_index";
    }  
}
