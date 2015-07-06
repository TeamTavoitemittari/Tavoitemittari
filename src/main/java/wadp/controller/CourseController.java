
package wadp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import wadp.domain.form.CourseForm;
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
        model.addAttribute("courses", courseService.getCourses());
        return "addcourse";
    }


    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(method = RequestMethod.POST)
    public String createCourse(RedirectAttributes redirectAttributes, @Valid @ModelAttribute Course course, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.course", bindingResult);
            redirectAttributes.addFlashAttribute("course", course);
            return "redirect:/course";

        }

        courseService.addCourse(course);
        return "redirect:mycourses";
    }




    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getCourse(Model model, @PathVariable Long id){
        Course course = courseService.getCourseById(id);
        courseService.sortCourseGrades(course);

        if(course==null){
            return "redirect:/mycourses";
        }

        model.addAttribute("course", course);
        User user = userService.getAuthenticatedUser();
        CourseProgressTracker tracker = progressService.getProgress(user, course);
        model.addAttribute("tracker", tracker);

        return "course";
    }

    @RequestMapping(value = "/{courseId}/{levelId}/{goalId}/{skillId}", method = RequestMethod.POST)
    public String learnCourseSkill(
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
     @RequestMapping(value="/{courseId}/update", method=RequestMethod.GET)
     public String getCourseForUpdate(RedirectAttributes redirectAttributes, @PathVariable Long courseId) throws JsonProcessingException{
        redirectAttributes.addFlashAttribute("course", new Course());
        
        ObjectMapper mapper = new ObjectMapper();
        
     
        redirectAttributes.addFlashAttribute("json", mapper.writeValueAsString(courseService.getCourseById(courseId))); 
        redirectAttributes.addFlashAttribute("updateCourse", courseService.getCourseById(courseId));
        return "redirect:/course#update";
       
    }
//    @RequestMapping(value="/{courseId}/update", method=RequestMethod.GET)
//    public String getCourseForUpdate(Model model, @PathVariable Long courseId){
//        model.addAttribute("course", new Course());
//        model.addAttribute("courses", courseService.getCourses());
//        model.addAttribute("updateCourse", courseService.getCourseById(courseId));
//        return "addcourse";
//    }
    
    @RequestMapping(value="/{courseId}/update", method=RequestMethod.POST)
    public String updateCourse(Model model, @PathVariable Long courseId, @ModelAttribute Course course){
        courseService.updateCourse(course, courseId);
        model.addAttribute("updateSuccessMessage", "Kurssi päivitetty!");
        model.addAttribute("course", courseService.getCourseById(courseId));
        return "/addcourse/#update";
    }
}
