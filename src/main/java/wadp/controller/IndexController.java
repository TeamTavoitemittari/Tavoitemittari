package wadp.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.domain.Course;
import wadp.domain.Exercise;
import wadp.domain.Goal;
import wadp.domain.Skill;
import wadp.service.CourseService;
import wadp.service.ExerciseService;
import wadp.service.GoalService;
import wadp.service.SkillService;
import wadp.service.UserService;

// Any request not handled by other controllers is redirected to index
@Controller
@RequestMapping("*")
public class IndexController {

    @Autowired
    private UserService userService;




    @RequestMapping(method = RequestMethod.GET)
    public String showIndex() {
        return "index";
    }

    @RequestMapping(value = "loginerror", method = RequestMethod.GET)
    public String showLoginError(Model model) {
        model.addAttribute("error", "Tarkista syöttämäsi tiedot!");
        return "index";
    }

    @RequestMapping(value = "/prototype", method = RequestMethod.GET)
    public String showPrototype() {
        return "courses";
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
