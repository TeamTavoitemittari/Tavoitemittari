package wadp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wadp.domain.*;
import wadp.service.*;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProgressService progressService;

    @Autowired
    private SkillService skillService;

    @Autowired
    GradeLevelService gradeLevelService;

    @Autowired
    GoalService goalService;

    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(method = RequestMethod.GET)
    public String ShowCreateCoursePage(Model model) {
        if (!model.containsAttribute("course")) {
            model.addAttribute("course", new Course());
        }
        model.addAttribute("coursesNotInUse", courseService.getCoursesNotInUseByTeacher(userService.getAuthenticatedUser()));
        model.addAttribute("coursesInUse", courseService.getCoursesInUseByTeacher(userService.getAuthenticatedUser()));

        return "addcourse";
    }

    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(method = RequestMethod.POST)
    public String createCourse(RedirectAttributes redirectAttributes, @Valid @ModelAttribute Course course, BindingResult bindingResult) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.course", bindingResult);
            redirectAttributes.addFlashAttribute("course", course);

            ObjectMapper mapper = new ObjectMapper();
            redirectAttributes.addFlashAttribute("invalidCourseAsJson", mapper.writeValueAsString(course));

            return "redirect:/course";

        }
        course.setTeacher(userService.getAuthenticatedUser());
        courseService.addCourse(course);
        redirectAttributes.addFlashAttribute("creationSuccessMessage", "Kurssi luotu!");
        return "redirect:/course";
        
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCourse(Model model, @PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        courseService.sortCourseGrades(course);
        courseService.sortCourseGoals(course);

        if (course == null) {
            return "redirect:/mycourses";
        }

        model.addAttribute("course", course);
        User user = userService.getAuthenticatedUser();
        CourseProgressTracker tracker = progressService.getProgress(user, course);
        model.addAttribute("tracker", tracker);
        model.addAttribute("currentStudent" , userService.getAuthenticatedUser());

        return "course";
    }



    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(value = "/{id}/{studentId}", method = RequestMethod.GET)
    public String getStudentGoalOMeter(Model model, @PathVariable Long id, @PathVariable Long studentId) {
        Course course = courseService.getCourseById(id);
        courseService.sortCourseGrades(course);
        courseService.sortCourseGoals(course);

        if (course == null) {
            return "redirect:/mycourses";
        }

        model.addAttribute("course", course);
        User user = userService.findById(studentId);
        CourseProgressTracker tracker = progressService.getProgress(user, course);
        model.addAttribute("tracker", tracker);
        model.addAttribute("users" , courseService.getCourseStudents(course));
        model.addAttribute("currentStudent", user);
        return "course";
    }

    @RequestMapping(value = "/{courseId}/{levelId}/{goalId}/{skillId}", method = RequestMethod.POST)
    public String confirmCourseSkillAsStudent(
            @PathVariable Long courseId,
            @PathVariable Long levelId,
            @PathVariable Long goalId,
            @PathVariable Long skillId) {

        Course course = courseService.getCourseById(courseId);
        GradeLevel gradeLevel = gradeLevelService.findGradeLevelById(levelId);
        Goal goal = goalService.findGoalById(goalId);
        Skill skill = skillService.findSkill(skillId);
        CourseProgressTracker tracker = progressService.getProgress(userService.getAuthenticatedUser(), course);
        progressService.swapSkillsStatus(tracker, gradeLevel, goal, skill);

        return "redirect:/course" + "/" + courseId;
    }

    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(value = "{userId}/{courseId}/{levelId}/{goalId}/{skillId}", method = RequestMethod.POST)
    public String confirmCourseSkillAsTeacher(
            @PathVariable Long userId,
            @PathVariable Long courseId,
            @PathVariable Long levelId,
            @PathVariable Long goalId,
            @PathVariable Long skillId) {

        Course course = courseService.getCourseById(courseId);
        GradeLevel gradeLevel = gradeLevelService.findGradeLevelById(levelId);
        Goal goal = goalService.findGoalById(goalId);
        Skill skill = skillService.findSkill(skillId);
        CourseProgressTracker tracker = progressService.getProgress(userService.findById(userId), course);
        progressService.swapSkillStatusAsTeacher(tracker, gradeLevel, goal, skill);
        
        return "redirect:/course/" + courseId + "/" + userId;
    }

    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(value = "/{courseId}/update", method = RequestMethod.GET)
    public String getCourseForUpdate(RedirectAttributes redirectAttributes, @PathVariable Long courseId) throws JsonProcessingException {
        redirectAttributes.addFlashAttribute("course", new Course());

        ObjectMapper mapper = new ObjectMapper();

        Course updateCourse = courseService.getCourseById(courseId);
        if (progressService.getCourseProgressTrackersByCourse(updateCourse).size() > 0) {
            redirectAttributes.addFlashAttribute("notEmptyMessage", "Tällä kurssilla on jo ilmoittautuneita oppilaita,"
                    + " joten et voi muokata sitä.");
            return "redirect:/course#update";
        }

        redirectAttributes.addFlashAttribute("updateCourseAsJson", mapper.writeValueAsString(courseService.getCourseById(courseId)));
        redirectAttributes.addFlashAttribute("updateCourse", courseService.getCourseById(courseId));
        return "redirect:/course#update";

    }

    @RequestMapping(value = "/{courseId}/update", method = RequestMethod.POST)
    public String updateCourse(RedirectAttributes redirectAttributes, @PathVariable Long courseId, @Valid @ModelAttribute Course course, BindingResult bindingResult) throws JsonProcessingException {

        if (bindingResult.hasErrors()) {
            ObjectMapper mapper = new ObjectMapper();
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.course", bindingResult);
            redirectAttributes.addFlashAttribute("course", course);
            redirectAttributes.addFlashAttribute("updateCourseAsJson", mapper.writeValueAsString(course));
            redirectAttributes.addFlashAttribute("updateCourse", courseService.getCourseById(courseId));
            return "redirect:/course#update";

        }

        courseService.updateCourse(course, courseId);

        redirectAttributes.addFlashAttribute("updateSuccessMessage", "Kurssi päivitetty!");
        redirectAttributes.addFlashAttribute("course", courseService.getCourseById(courseId));
        return "redirect:/course#update";
    }

    @RequestMapping(value = "/{courseId}/join", method = RequestMethod.POST)
    public String joinCourse(RedirectAttributes redirectAttributes, @PathVariable Long courseId) {
        if (progressService.getProgress(userService.getAuthenticatedUser(), courseService.getCourseById(courseId)) != null) {

            redirectAttributes.addFlashAttribute("alreadyJoinedMessage", "Olet jo liittynyt kurssille!");
            return "redirect:/mycourses";
        }
        if (courseService.getCourseById(courseId).getInUse()==true){
         courseService.joinCourse(userService.getAuthenticatedUser(), courseService.getCourseById(courseId));
         redirectAttributes.addFlashAttribute("joinedSuccessMessage", "Sinut on liitetty kurssille!");
        }
        return "redirect:/mycourses";

    }
    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(value = "/{courseId}/delete", method = RequestMethod.DELETE)
    public String deleteCourse(RedirectAttributes redirectAttributes, @PathVariable Long courseId) {

     
        if (courseService.getCourseById(courseId).getTeacher().equals(userService.getAuthenticatedUser())){
         courseService.deleteCourse(courseId);
         redirectAttributes.addFlashAttribute("deleteSuccessMessage", "Kurssi poistettu!");
        }
        return "redirect:/course#owncourses";
        
    }
    
    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(value = "/{courseId}/publish", method = RequestMethod.PUT)
    public String publishCourse(RedirectAttributes redirectAttributes, @PathVariable Long courseId) {
        
     if (courseService.getCourseById(courseId).getTeacher().equals(userService.getAuthenticatedUser())){
         courseService.publishCourse(courseId);
         redirectAttributes.addFlashAttribute("publishSuccessMessage", "Kurssi julkaistu!");
        }
        return "redirect:/course#owncourses";    
    }


}
