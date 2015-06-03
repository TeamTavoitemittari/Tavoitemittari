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
    
    
    //for testing
   @PostConstruct
   private void init(){
       //deleting old inits so heroku works
       UserService.deleteAll();
       UserService.createUser("oppilas@a.com","oppilas","Matti Meikalainen","student");
       UserService.createUser("ope@a.com","ope","Olli Opettaja", "teacher");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(RedirectAttributes redirectAttributes, @ModelAttribute("user") @Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            UserService.createUser(userForm.getEmail(), userForm.getPassword(), userForm.getName(), userForm.getUserRole());
        } catch (EmailAlreadyRegisteredException ex) {
            bindingResult.addError(new FieldError("user", "email", "Sähköpostiosoite on jo rekisteröity palveluun!"));
            return "register";
        }
        redirectAttributes.addFlashAttribute("registeredEmail", userForm.getEmail());
        
        return "redirect:welcome";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String Register(Model model) {
        model.addAttribute("user", new UserForm());
        return "register";
    }

    
        
    
    
    
}
