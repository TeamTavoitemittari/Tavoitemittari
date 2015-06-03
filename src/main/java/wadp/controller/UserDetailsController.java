package wadp.controller;

import java.util.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import static org.apache.maven.wagon.PathUtils.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wadp.domain.Course;
import wadp.domain.Exercise;
import wadp.domain.Goal;
import wadp.domain.Skill;
import wadp.domain.User;
import wadp.domain.form.ProperPasswordForm;
import wadp.domain.form.UserForm;
import wadp.service.CourseService;
import wadp.service.EmailAlreadyRegisteredException;
import wadp.service.ExerciseService;
import wadp.service.GoalService;
import wadp.service.SkillService;
import wadp.service.UserService;

// Any request not handled by other controllers is redirected to index
@Controller
@RequestMapping("*")
public class UserDetailsController {
    @Autowired
    private UserService UserService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SkillService skillService;
    
   

    
   
        
    @RequestMapping(value = "/userdetails", method = RequestMethod.GET)
    public String ShowUserDetails(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        model.addAttribute("newpassword", new ProperPasswordForm());
        return "userdetails";
    }
    
    
    @RequestMapping(value = "/userdetails", method = RequestMethod.POST)
    public String ChangeUserPassword(RedirectAttributes redirectAttributes, @ModelAttribute("newpassword") @Valid ProperPasswordForm newpassword, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            
           
        return "userdetails";
        }
        
        
        userService.ChangePassword(newpassword.getPassword());
                
        redirectAttributes.addFlashAttribute("message", "Salasana vaihdettu.");
        
        return "redirect:userdetails";
    }



}
