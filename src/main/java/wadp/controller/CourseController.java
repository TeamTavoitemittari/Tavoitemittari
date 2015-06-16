
package wadp.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.User;
import wadp.domain.form.CourseForm;
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
    
    
    @Autowired
    private CourseService CourseService;
    
    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(method = RequestMethod.GET)
    public String ShowCreateCoursePage(Model model) {
        model.addAttribute("addcourse", new CourseForm());
        return "addcourse";
    }
    
    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(method = RequestMethod.POST)
    public String createCourse(@ModelAttribute("addcourse") @Valid CourseForm addcourse, BindingResult bindingResult) {
        
        if(bindingResult.hasErrors()){
            return "addcourse";
        }
        
            Course newCourse = new Course();
            newCourse.setName(addcourse.getName());
            newCourse.setDescription(addcourse.getDescription());
            CourseService.addCourse(newCourse);
        return "redirect:mycourses";
    }

  
    
    
//    @RequestMapping(method = RequestMethod.POST)
//    public void createCourse(){
//        //TODO: code for creation of new course
//    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCourse(Model model, @PathVariable Long id){
        Course course = courseService.getCourseById(id);
        
        if(course==null){
            return "redirect:/mycourses";
        }
        model.addAttribute("course", course);
        //TODO using progress tracker properly
        //User user = userService.getAuthenticatedUser();
        //CourseProgressTracker tracker = progressService.getProgress(user, course);
        model.addAttribute("tracker", tracker);
        
        return "course";
    }
}
