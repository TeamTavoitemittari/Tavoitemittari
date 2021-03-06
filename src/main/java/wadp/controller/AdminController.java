package wadp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wadp.domain.Course;
import wadp.domain.PasswordForgottenNotice;
import wadp.domain.User;
import wadp.service.CourseService;
import wadp.service.PasswordForgettingService;
import wadp.service.UserService;

/**
 * Controller that handles all functions that are only available
 * to the admin.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PasswordForgettingService passwordService;

    /**
     * Returns all users and courses in the system.
     * @param session the session of the admin.
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(method = RequestMethod.GET)
    public String getStudentsAndCourses(HttpSession session, Model model) {
        session.setMaxInactiveInterval(60 * 60 * 3);
        model.addAttribute("users", userService.list());
        model.addAttribute("courses", courseService.getCourses());
        return "admin_index";
    }

    /**
     * Returns a view of the user specifcs
     * @param id user id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("courses", courseService.getUsersCourses(user));
        return "student";
    }


    /**
     * Removes the user's enrollment to a course
     * @param courseId
     * @param userId
     * @return the course view
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/{courseId}/{userId}/remove", method = RequestMethod.DELETE)
    public String removeUserFromCourse(RedirectAttributes redirectAttributes, @PathVariable Long courseId, @PathVariable Long userId) {
        courseService.RemoveUserFromCourse(courseId, userId);
        return "redirect:/course/" + courseId + "/goalometer";
    }

    /**
     * Deletes a user
     * @param userId
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/{userId}/delete", method = RequestMethod.DELETE)
    public String deleteUser(RedirectAttributes redirectAttributes, @PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin";
    }

    /**
     * Gets a view with users that are on a specific course
     * @param id course id
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "filter/{id}", method = RequestMethod.GET)
    public String getUsersOnCourse(Model model, @PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        List<User> users = courseService.getCourseStudents(course);
        users.add(courseService.getCourseById(id).getTeacher());
        model.addAttribute("users", users);
        model.addAttribute("courses", courseService.getCourses());
        return "admin_index";
    }

    /**
     * Returns a view with all forgotten password notices
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/passwords", method = RequestMethod.GET)
    public String getLostPasswordsView(Model model) {
        List<PasswordForgottenNotice> forgottenPasswords = passwordService.getForgottenPasswords();
        if (forgottenPasswords.size() > 0) {
            model.addAttribute("forgottenPasswords", forgottenPasswords);
        }
        return "forgottenpasswords";
    }

    /**
     * Returns a view where the admin can reset the password of a specific user
     * @param userId user whose password will be reset
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/password/{userId}")
    public String getAdminUpdatePasswordView(Model model, @PathVariable Long userId) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "admin_updatepassword";
    }

    /**
     * Updates a specific password
     * @param password new password
     * @param userId id of the user whose password it is
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/password/update/{userId}")
    public String ChangeUserPassword(Model model, @RequestParam String password, @PathVariable Long userId) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        userService.changePasswordAsAdmin(user, password);
        model.addAttribute("message", "Salasana vaihdettu.");
        passwordService.deleteForgottenPasswordForUser(user);
        return "admin_updatepassword";
    }
}
