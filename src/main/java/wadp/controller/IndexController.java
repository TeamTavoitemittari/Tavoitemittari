package wadp.controller;

import java.util.*;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.domain.*;
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
public class IndexController {

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
    public String showIndex() {
        createDummyCourse();
        createDummyCourseWithoutUsers();
        if (userService.getAuthenticatedUser() != null) {
            return "redirect:mycourses";
        } else {
            return "index";
        }
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

    @Transactional
    private void createDummyCourse() {

        if (courseService.getCourses().size() > 0) {
            return;
        }

        Course course = new Course();
        course.setName("Tähtitiede 1");
        course.setDescription("Kurssilla perehdytään erinäisiin taivankappaleisiina alkaen omasta aurinkokunnastamme"
                + "ja edeten hiljalleen galaksin muihin osiin. Kurssin jälkeen tiedät mitä eroa on mustalla aukolla"
                + "ja valkoisella kääpiöllä sekä tunnistat tähtitaivaalta eri tähtikuviot.");

        GradeLevel level1 = new GradeLevel();
        level1.setGrade("5-6");
        Goal goal1 = new Goal();
        goal1.setName("Tähtikuviot");

        Skill skill1 = new Skill("Oppilas tunnistaa tähtikuvioita", "667, 12a, Käy illalla kotona ulkona ja piirrä kolme valitsemaasi tähtikuviota paperille, Kirjota 200 sanan tiivistelmä kirjan luvusta 6: Meidän galaksimme");

        skillService.addSkill(skill1);

        Skill skill2 = new Skill("Oppilas ymmärtää tähtikuvioiden suhteelliset etäisyydet", "758, 827, 100, 220, 12, 16, 16");

        skillService.addSkill(skill2);

        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(skill1);
        skills1.add(skill2);

        goal1.setSkills(skills1);
        goalService.addGoal(goal1);

        Goal goal2 = new Goal();
        goal2.setName("Painovoima");

        Skill skill3 = new Skill("Oppilas osaa selittää painovoiman vaikutuksen hänen fyysiseen ympäristöönsä", "101b, 76, Katso Cosmos: A Spacetime Odyssey -sarjan seitsemästoista jakso ja kirjoita siitä 200 sanan referaatti, 63");

        skillService.addSkill(skill3);

        Skill skill4 = new Skill("Oppilas ymmärtää painovoiman suhteen planeettojen massaan", "758, 827,100,220,12,16,19");

        skillService.addSkill(skill4);

        ArrayList<Skill> skills2 = new ArrayList<>();

        skills2.add(skill4);
        skills2.add(skill3);

        goal2.setSkills(skills2);

        goalService.addGoal(goal2);

        ArrayList<Goal> goals1 = new ArrayList<Goal>();
        goals1.add(goal1);
        goals1.add(goal2);

        level1.setGoals(goals1);

        gradeService.addGradeLevel(level1);

        GradeLevel level2 = new GradeLevel();
        level2.setGrade("7-8");

        Goal goal3 = new Goal();
        goal3.setName("Mustat aukot");

        Skill skill5 = new Skill("Oppilas osaa kuvata mustan aukon syntymisprosessin", "89, 10");

        skillService.addSkill(skill5);

        ArrayList<Skill> skillz = new ArrayList<>();
        skillz.add(skill5);
        goal3.setSkills(skillz);

        goalService.addGoal(goal3);

        ArrayList<Goal> goals2 = new ArrayList<Goal>();
        goals2.add(goal3);
        level2.setGoals(goals2);
        gradeService.addGradeLevel(level2);

        GradeLevel level3 = new GradeLevel();
        level3.setGrade("9-10");

        Goal goal4 = new Goal();
        goal4.setName("Astrofysiikka");

        Skill skill6 = new Skill("Oppilas taitaa astrofysiikan salat", "Laske auringon massa, 893, Lue Carl Saganin Kosmos ja kirjoita siitä neljän sivun referaatti. ");

        skillService.addSkill(skill6);

        ArrayList<Skill> skills4 = new ArrayList<Skill>();
        skills4.add(skill6);
        goal4.setSkills(skills4);

        goalService.addGoal(goal4);

        ArrayList<Goal> goals4 = new ArrayList<Goal>();
        goals4.add(goal4);

        level3.setGoals(goals4);

        gradeService.addGradeLevel(level3);

        ArrayList<GradeLevel> levels = new ArrayList<GradeLevel>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        course.setGradeLevels(levels);
        courseService.addCourse(course);

        User user = userService.findUserByEmail("oppilas@a.com");

        CourseProgressTracker tracker = new CourseProgressTracker(user, course);

        for (GradeProgressTracker gradeTracker : tracker.getGradeLevels().values()) {
            for (GoalProgressTracker goalTracker : gradeTracker.getGoals().values()) {
                progressService.saveGoalTracker(goalTracker);
                HashMap<Skill, Comment> comments = new HashMap<Skill, Comment>();
                for (Skill skill : goalTracker.getSkills().keySet()) {
                    Comment comment = commentService.addComment(skill);
                    comments.put(skill, comment);
                }
                goalTracker.setComments(comments);
            }
            progressService.saveGradeTracker(gradeTracker);
        }

        Comment comment1 = tracker.getGradeLevels().get(level3).getGoals().get(goal4).getComments().get(skill6);
        commentService.updateComment(comment1, "Tämä oli hyvin kiinnostava osa-alue"
                + " mutta materiaali oli aika vaikeaselkoista. Video auttoi paljon. /Teemu");

        Comment comment2 = tracker.getGradeLevels().get(level1).getGoals().get(goal1).getComments().get(skill1);
        commentService.updateComment(comment2, "Teit tänään kovasti töitä ja autoit myös muita oppilaita. "
                + "Jatka samaan malliin! /Ope");

        progressService.saveCourseTracker(tracker);

        progressService.updateSkillStatus(tracker, skill6, Status.STUDENT_CONFIRMED);
        progressService.updateSkillStatus(tracker, skill1, Status.TEACHER_CONFIRMED);
        progressService.updateSkillStatus(tracker, skill2, Status.TEACHER_CONFIRMED);

    }

    @Transactional
    private void createDummyCourseWithoutUsers() {

        if (courseService.getCourses().size() > 1) {
            return;
        }

        Course course = new Course();
        course.setName("Tähtitiede 2");
        course.setDescription("Kurssilla perehdytään erinäisiin taivankappaleisiina alkaen omasta aurinkokunnastamme"
                + "ja edeten hiljalleen galaksin muihin osiin. Kurssin jälkeen tiedät mitä eroa on mustalla aukolla"
                + "ja valkoisella kääpiöllä sekä tunnistat tähtitaivaalta eri tähtikuviot.");

        GradeLevel level1 = new GradeLevel();
        level1.setGrade("5-6");
        Goal goal1 = new Goal();
        goal1.setName("Tähtikuviot");

        Skill skill1 = new Skill("Oppilas tunnistaa tähtikuvioita", "667, 12a, Käy illalla kotona ulkona ja piirrä kolme valitsemaasi tähtikuviota paperille, Kirjota 200 sanan tiivistelmä kirjan luvusta 6: Meidän galaksimme");

        skillService.addSkill(skill1);

        Skill skill2 = new Skill("Oppilas ymmärtää tähtikuvioiden suhteelliset etäisyydet", "758, 827, 100, 220, 12, 16, 16");

        skillService.addSkill(skill2);

        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(skill1);
        skills1.add(skill2);

        goal1.setSkills(skills1);
        goalService.addGoal(goal1);

        Goal goal2 = new Goal();
        goal2.setName("Painovoima");

        Skill skill3 = new Skill("Oppilas osaa selittää painovoiman vaikutuksen hänen fyysiseen ympäristöönsä", "101b, 76, Katso Cosmos: A Spacetime Odyssey -sarjan seitsemästoista jakso ja kirjoita siitä 200 sanan referaatti, 63");

        skillService.addSkill(skill3);

        Skill skill4 = new Skill("Oppilas ymmärtää painovoiman suhteen planeettojen massaan", "758, 827,100,220,12,16,19");

        skillService.addSkill(skill4);

        ArrayList<Skill> skills2 = new ArrayList<>();

        skills2.add(skill4);
        skills2.add(skill3);

        goal2.setSkills(skills2);

        goalService.addGoal(goal2);

        ArrayList<Goal> goals1 = new ArrayList<Goal>();
        goals1.add(goal1);
        goals1.add(goal2);

        level1.setGoals(goals1);

        gradeService.addGradeLevel(level1);

        GradeLevel level2 = new GradeLevel();
        level2.setGrade("7-8");

        Goal goal3 = new Goal();
        goal3.setName("Mustat aukot");

        Skill skill5 = new Skill("Oppilas osaa kuvata mustan aukon syntymisprosessin", "89, 10");

        skillService.addSkill(skill5);

        ArrayList<Skill> skillz = new ArrayList<>();
        skillz.add(skill5);
        goal3.setSkills(skillz);

        goalService.addGoal(goal3);

        ArrayList<Goal> goals2 = new ArrayList<Goal>();
        goals2.add(goal3);
        level2.setGoals(goals2);
        gradeService.addGradeLevel(level2);

        GradeLevel level3 = new GradeLevel();
        level3.setGrade("9-10");

        Goal goal4 = new Goal();
        goal4.setName("Astrofysiikka");

        Skill skill6 = new Skill("Oppilas taitaa astrofysiikan salat", "Laske auringon massa, 893, Lue Carl Saganin Kosmos ja kirjoita siitä neljän sivun referaatti. ");

        skillService.addSkill(skill6);

        ArrayList<Skill> skills4 = new ArrayList<Skill>();
        skills4.add(skill6);
        goal4.setSkills(skills4);

        goalService.addGoal(goal4);

        ArrayList<Goal> goals4 = new ArrayList<Goal>();
        goals4.add(goal4);

        level3.setGoals(goals4);

        gradeService.addGradeLevel(level3);

        ArrayList<GradeLevel> levels = new ArrayList<GradeLevel>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        course.setGradeLevels(levels);
        courseService.addCourse(course);

    }
}
