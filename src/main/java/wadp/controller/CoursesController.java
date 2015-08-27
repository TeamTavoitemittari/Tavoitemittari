
package wadp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.domain.Course;
import wadp.service.CourseService;
import wadp.service.GradeService;
import wadp.service.UserService;

/**
 * Controller that handles requests related to more than one course.
 */
@Controller
@RequestMapping("/mycourses")
public class CoursesController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private GradeService gradeService;

    /**
     * Returns the view which has a list of courses which are relevant to this specific user.
     * @return the view of the user's courses
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showCoursesPage(Model model) {
        if (userService.getAuthenticatedUser().getUserRole().equals("student")) {
            model.addAttribute("courses", courseService.getCoursesInUse());
            model.addAttribute("owncourses", courseService.getUsersUncompletedCourses(userService.getAuthenticatedUser()));
            model.addAttribute("completed", gradeService.getStudentGrades(userService.getAuthenticatedUser()));

        }
        if (userService.getAuthenticatedUser().getUserRole().equals("teacher")) {

            List<Course> teachersCourses = courseService.getCoursesInUseByTeacher(userService.getAuthenticatedUser());
            model.addAttribute("teachersCourses", teachersCourses);
            model.addAttribute("counts", courseService.GetStudentCountsForTeachersEachCourseAsMap(userService.getAuthenticatedUser(), teachersCourses));

        }
        if (userService.getAuthenticatedUser().getUserRole().equals("admin")) {
            return "redirect:/admin";
        }
        return "mycourses";

    }
}
