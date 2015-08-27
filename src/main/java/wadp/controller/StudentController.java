package wadp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wadp.domain.Course;
import wadp.domain.User;
import wadp.service.CourseService;
import wadp.service.UserService;

/**
 * Controller for student administration functions
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    /**
     * @return view of all students and courses
     */
    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(method = RequestMethod.GET)
    public String getStudentsAndCourses(Model model) {
        model.addAttribute("students", userService.findUserByRole("student"));
        model.addAttribute("courses", courseService.getCoursesInUseByTeacher(userService.getAuthenticatedUser()));
        return "students";
    }


    /**
     * @param id user id
     * @return specific student view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getStudent(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("courses", courseService.getUsersCoursesWithTeacher(user, userService.getAuthenticatedUser()));
        return "student";
    }

    /**
     * Removes a student from a specific course
     * @param courseId
     * @param userId
     * @return course goalometer
     */
    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(value = "/{courseId}/{userId}/remove", method = RequestMethod.DELETE)
    public String removeStudentFromCourse(RedirectAttributes redirectAttributes, @PathVariable Long courseId, @PathVariable Long userId) {
      if (courseService.getCourseById(courseId).getTeacher().equals(userService.getAuthenticatedUser())){
       courseService.RemoveUserFromCourse(courseId, userId);
      }
       return "redirect:/course/" + courseId + "/goalometer";
    }

    /**
     *
     * @param id course id
     * @return a view of students on a specific course.
     */
    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(value = "filter/{id}", method = RequestMethod.GET)
    public String getFilteredStudents(Model model, @PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("students", courseService.getCourseStudents(course));
        model.addAttribute("courses", courseService.getCoursesInUseByTeacher(userService.getAuthenticatedUser()));
        return "students";
    }  
}
