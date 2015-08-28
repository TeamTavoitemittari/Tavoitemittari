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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller that handles requests related to the creation of new users.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
 
    
    @Autowired
    private UserService userService;

    //only for testing
    @PostConstruct
    private void init() {
        if (userService.list().isEmpty()) {
            userService.clearUsers();
            userService.createUser("admin@a.com", "admin", "Eve Lappalainen", "admin");
        }
    }

    /**
     * Creates a new user
     * @param user registration information object
     * @return registration view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String createUser(RedirectAttributes redirectAttributes, @ModelAttribute("user") @Valid UserForm user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/register";

        }

        try {
            userService.createUser(user.getEmail(), user.getPassword(), user.getName(), "student");
        } catch (EmailAlreadyRegisteredException ex) {
            bindingResult.addError(new FieldError("user", "email", "Sähköpostiosoite on jo rekisteröity palveluun!"));
            return "register";
        }

        redirectAttributes.addFlashAttribute("registeredEmail", user.getEmail());
        return "redirect:welcome";

    }

    /**
     * Allows the admin to use the registration form to create new teachers/students
     * @param user object for registration info
     * @return registration view
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/as_admin", method = RequestMethod.POST)
    public String createUserAsAdmin(RedirectAttributes redirectAttributes, @ModelAttribute("user") @Valid UserForm user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/register";

        }

        try {
            userService.createUser(user.getEmail(), user.getPassword(), user.getName(), user.getUserRole());
        } catch (EmailAlreadyRegisteredException ex) {
            bindingResult.addError(new FieldError("user", "email", "Sähköpostiosoite on jo rekisteröity palveluun!"));
            return "register";
        }

        return "redirect:/admin";

    }

    /**
     * Returns the registration form used by the admin to create new students/teachers
     * @return registration view
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/as_admin", method = RequestMethod.GET)
    public String RegisterAsAdmin(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("post_address", "/register/as_admin");
            model.addAttribute("user", new UserForm());
        }
        return "register";
    }

    /**
     * Returns the registration view for non-admin users.
     * @return registration view.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String Register(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("post_address" ,"/register");
            model.addAttribute("user", new UserForm());
        }
        return "register";
    }

}
