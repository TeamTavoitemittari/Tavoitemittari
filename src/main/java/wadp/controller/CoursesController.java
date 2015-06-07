
package wadp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.service.CourseService;

@Controller
@RequestMapping("/mycourses")
public class CoursesController {
    
    @Autowired
    private CourseService courseService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String showCoursesPage(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "mycourses";
    }
}
