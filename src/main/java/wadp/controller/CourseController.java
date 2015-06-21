
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String createCourse(RedirectAttributes redirectAttributes, @ModelAttribute Course course){
   
    ///temp solution    
    if (course.getGradeLevels().size() == 3){    
     course.getGradeLevels().get(0).setGrade("9-10");
     course.getGradeLevels().get(1).setGrade("7-8");
     course.getGradeLevels().get(2).setGrade("5-6");  
    }
    CourseService.addCourse(course);
    return "redirect:mycourses";
    }
    
//    @PreAuthorize("hasAuthority('teacher')")
//    @RequestMapping(method = RequestMethod.POST)
//    public String createCourse(@ModelAttribute("addcourse") @Valid CourseForm addcourse, BindingResult bindingResult) {
//        
//        if(bindingResult.hasErrors()){
//            return "addcourse";
//        }
//        
//            Course newCourse = new Course();
//            newCourse.setName(addcourse.getName());
//            newCourse.setDescription(addcourse.getDescription());
//            CourseService.addCourse(newCourse);
//        return "redirect:mycourses";
//    }


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
