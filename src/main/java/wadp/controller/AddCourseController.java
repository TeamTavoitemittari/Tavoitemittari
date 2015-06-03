package wadp.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.service.UserService;

import javax.validation.Valid;
import static org.apache.maven.wagon.PathUtils.user;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wadp.domain.Course;
import wadp.domain.form.CourseForm;
import wadp.service.CourseService;

@Controller
@RequestMapping("/register")
public class AddCourseController {

    @Autowired
    private CourseService CourseService;
    
    

    @RequestMapping(method = RequestMethod.POST)
    public String createCourse(RedirectAttributes redirectAttributes, @ModelAttribute("courseForm") @Valid CourseForm courseForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addcourse";
        }
            Course newCourse = new Course();
            newCourse.setName(courseForm.getName());
            newCourse.setDescription(courseForm.getDescription());
            CourseService.addCourse(newCourse);
        return "redirect:courses";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String Register(Model model) {
        model.addAttribute("courseForm", new CourseForm());
        return "addcourse";
    }

    
        
    
    
    
}
