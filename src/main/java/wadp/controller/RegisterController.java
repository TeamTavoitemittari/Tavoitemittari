package wadp.controller;

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

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService UserService;

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid UserForm user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            UserService.createUser(user.getEmail(), user.getPassword(), user.getName(), user.getUserRole());
        } catch (EmailAlreadyRegisteredException ex) {
            bindingResult.addError(new FieldError("user", "email", "This email address already registered!"));
            return "register";
        }
        
        return "redirect:view";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String Register(Model model) {
        model.addAttribute("user", new UserForm());
        return "register";
    }

    
        
    
    
    
}
