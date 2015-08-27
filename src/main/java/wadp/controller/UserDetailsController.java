package wadp.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wadp.domain.form.ProperPasswordForm;
import wadp.domain.form.SchoolClassForm;
import wadp.service.*;

/**
 * Controller for handling updates to a user's information and password forgetting requests
 */
@Controller
@RequestMapping("*")
public class UserDetailsController {


    @Autowired
    private UserService userService;

    @Autowired
    private PasswordForgettingService passwordForgettingService;


    /**
     * @return view where user can update their own details
     */
    @RequestMapping(value = "/userdetails", method = RequestMethod.GET)
    public String ShowUserDetails(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());

        if (!model.containsAttribute("newpassword")) {
            model.addAttribute("newpassword", new ProperPasswordForm());
        }
        if (!model.containsAttribute("schoolClass")) {
            model.addAttribute("schoolClass", new SchoolClassForm());
        }
        return "userdetails";
    }

    /**
     * @return modal with additional info
     */
    @RequestMapping(value = "/userdetails_info", method = RequestMethod.GET)
    public String ShowUserDetailsInfoPage(Model model) {
        model.addAttribute("message", "Omat tiedot -sivulla voit vaihtaa salasanasi. Uuden salasanan pitää olla vähintään 8-merkkinen.");

        return "userdetails_template";
    }

    /**
     * @return view that confirms the password change
     */
    @RequestMapping(value = "/userdetails_passwordchanged", method = RequestMethod.GET)
    public String showPasswordChangedPage(Model model) {

        return "userdetails_template";
    }

    /**
     * Changes the password for a user
     * @param newpassword
     * @return confirmation page for password change
     */
    @RequestMapping(value = "/userdetails", method = RequestMethod.POST)
    public String ChangeUserPassword(RedirectAttributes redirectAttributes, @ModelAttribute("newpassword") @Valid ProperPasswordForm newpassword, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newpassword", bindingResult);
            redirectAttributes.addFlashAttribute("newpassword", newpassword);
            return "redirect:/userdetails";


        }

        try {
            userService.ChangePassword(newpassword.getPassword());
        } catch (AuthenticatedUserIsNullException ex) {

            bindingResult.addError(new FieldError("newpassword","", "Autentikointi ongelma."));
            return "userdetails";

        }
        redirectAttributes.addFlashAttribute("message", "Salasana vaihdettu.");

        return "redirect:userdetails_passwordchanged";
    }

    /**
     * Changes the class (f.e. 7A) of a user.
     * @param schoolClass school class form
     * @return user details viewl
     */
    @RequestMapping(value = "/userdetails/changeclass", method = RequestMethod.POST)
    public String changeUserClass(RedirectAttributes redirectAttributes, @ModelAttribute("schoolClass") @Valid SchoolClassForm schoolClass, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.schoolClass", bindingResult);
            redirectAttributes.addFlashAttribute("schoolClass", schoolClass);
            return "redirect:/userdetails";
        }

        try {
            userService.changeSchoolClass(schoolClass.getSchoolClass());
        } catch (AuthenticatedUserIsNullException ex) {

            bindingResult.addError(new FieldError("schoolClass","", "Autentikointi ongelma."));
            return "userdetails";

        }


        return "redirect:/userdetails";
    }

    /**
     * @return view where users report that they have forgotten their password
     */
    @RequestMapping(value="/passwordretrieval", method=RequestMethod.GET)
    public String getPasswordRetrievalPage(){
        return "passwordretrieval";
    }

    /**
     * Sends report of a forgotten password
     * @param email with which the user claims to have registered
     * @return
     */
    @RequestMapping(value="/passwordretrieval", method=RequestMethod.POST)
    public String sendPasswordForgottenReport(RedirectAttributes redirectAttributes, @ModelAttribute("email") String email){
        if(userService.findUserByEmail(email)==null){
            redirectAttributes.addFlashAttribute("sentFailureMessage", "Lähetys epäonnistui, käyttäjää ei löytynyt tällä sähköpostiosoitteella");
        } else if (passwordForgettingService.userHasForgottenPassword(userService.findUserByEmail(email))) {
            redirectAttributes.addFlashAttribute("sentFailureMessage", "Ilmoitus on jo tehty!");
        } else{
            passwordForgettingService.reportPasswordForgotten(userService.findUserByEmail(email));
            redirectAttributes.addFlashAttribute("sentSuccessMessage", "Ilmoitus lähetetty ylläpitäjälle!");
        }
        return "redirect:/passwordretrieval";
    }



}
