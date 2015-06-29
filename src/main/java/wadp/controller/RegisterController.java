package wadp.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.domain.form.UserForm;
import wadp.service.UserService;
import wadp.service.EmailAlreadyRegisteredException;

import javax.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService UserService;
    
    
    //testausta varten
   @PostConstruct
   private void init(){
       //poistetaan vanhat herokua varten
       UserService.clearUsers();
       UserService.createUser("oppilas@a.com","oppilas","Matti Meikalainen","student");
       UserService.createUser("ope@a.com","ope","Olli Opettaja", "teacher");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(RedirectAttributes redirectAttributes, @ModelAttribute("user") @Valid UserForm user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            
            
           
       redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
       redirectAttributes.addFlashAttribute("user", user);
       return "redirect:/register";

            
          
        }

        try {
            UserService.createUser(user.getEmail(), user.getPassword(), user.getName(), user.getUserRole());
        } catch (EmailAlreadyRegisteredException ex) {
            bindingResult.addError(new FieldError("user", "email", "Sähköpostiosoite on jo rekisteröity palveluun!"));
            return "register";
        }
        redirectAttributes.addFlashAttribute("registeredEmail", user.getEmail());
        
        return "redirect:welcome";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String Register(Model model) {
        if (!model.containsAttribute("user")) {
          model.addAttribute("user", new UserForm());
        }
        return "register";
    }

    
        
    
    
    
}
