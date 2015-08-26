package wadp.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.service.CommentService;
import wadp.service.CourseService;
import wadp.service.GoalService;
import wadp.service.GradeLevelService;
import wadp.service.ProgressService;
import wadp.service.SkillService;
import wadp.service.UserService;

// Any request not handled by other controllers is redirected to index
@Controller
@RequestMapping("*")
public class IndexController implements ErrorController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private GradeLevelService gradeService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private ProgressService progressService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public String showIndex(HttpSession session) {
//        courseService.createDummyCourse();
//        courseService.createDummyCourseWithoutUsers();
        session.setMaxInactiveInterval(60 * 60 * 3);
        if (userService.getAuthenticatedUser() != null) {
            return "redirect:mycourses";
        } else {
            return "index";
        }
    }

    private static final String PATH = "/error";


    
    
    @RequestMapping(value = PATH)
    public String error(Model model, HttpServletRequest request, Exception exception) {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = "loginerror", method = RequestMethod.GET)
    public String showLoginError(Model model) {
        model.addAttribute("error", "Tarkista syöttämäsi tiedot!");
        return "index";
    }

    @RequestMapping(value = "/prototype", method = RequestMethod.GET)
    public String showPrototype() {
        return "mycourses";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String showWelcomePage(Model model) {
        model.addAttribute("users", userService.list());
        return "welcome";
    }

    @RequestMapping(value = "/coursedemo", method = RequestMethod.GET)
    public String getCourseDemo(Model model) {
        return "course";
    }

    @RequestMapping(value = "/addcourse", method = RequestMethod.GET)
    public String getAddCourse(Model model) {
        return "addCourse";
    }

    
}
