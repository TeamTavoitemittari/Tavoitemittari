package wadp.controller;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
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

@Controller
@RequestMapping("/courses")
public class CoursesController {

    private boolean added = false;


    @Autowired
    private CourseService courseService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private SkillService skillService;

    @RequestMapping(method = RequestMethod.GET)
    public String showCoursesPage(Model model) {
        model.addAttribute("courses", courseService.getCourses());
        return "courses";
    }

    @PostConstruct
    private void init() {
        courseService.deleteAll();
        createDummyCourse();
    }

    private void createDummyCourse() {
        if (added) {
            return;
        }
        if (courseService.getCourses().size() > 0) {
            return;
        }

        Course course = new Course();
        course.setName("Tähtitiede 1");
        course.setDescription("Kurssilla perehdytään erinäisiin taivankappaleisiina alkaen omasta aurinkokunnastamme"
                + "ja edeten hiljalleen galaksin muihin osiin. Kurssin jälkeen tiedät mitä eroa on mustalla aukolla"
                + "ja valkoisella kääpiöllä sekä tunnistat tähtitaivaalta eri tähtikuviot.");

        Goal goal1 = new Goal();
        goal1.setGrade("5-6");

        Skill skill1 = new Skill("Tähtikuviot");

        Exercise exer1 = new Exercise("667");
        Exercise exer2 = new Exercise("12a");
        Exercise exer3 = new Exercise("Käy illalla kotona ulkona ja piirrä kolme valitsemaasi tähtikuviota paperille.");
        Exercise exer4 = new Exercise("Kirjota 200 sanan tiivistelmä kirjan luvusta 6: Meidän galaksimme");

        skill1.addExercise(exer1);
        skill1.addExercise(exer2);
        skill1.addExercise(exer3);
        skill1.addExercise(exer4);

        exerciseService.addExercise(exer1);
        exerciseService.addExercise(exer2);
        exerciseService.addExercise(exer3);
        exerciseService.addExercise(exer4);

        skillService.addSkill(skill1);

        Skill skill2 = new Skill("Painovoima");

        Exercise exer5 = new Exercise("758");
        Exercise exer6 = new Exercise("827");
        Exercise exer7 = new Exercise("100");
        Exercise exer8 = new Exercise("220");
        Exercise exer9 = new Exercise("12");
        Exercise exer10 = new Exercise("16");
        Exercise exer11 = new Exercise("19");

        skill2.addExercise(exer5);
        skill2.addExercise(exer6);
        skill2.addExercise(exer7);
        skill2.addExercise(exer8);
        skill2.addExercise(exer9);
        skill2.addExercise(exer10);
        skill2.addExercise(exer11);

        exerciseService.addExercise(exer5);
        exerciseService.addExercise(exer6);
        exerciseService.addExercise(exer7);
        exerciseService.addExercise(exer8);
        exerciseService.addExercise(exer9);
        exerciseService.addExercise(exer10);
        exerciseService.addExercise(exer11);

        skillService.addSkill(skill2);

        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(skill1);
        skills1.add(skill2);

        goal1.setSkills(skills1);
        goalService.addGoal(goal1);

        Goal goal2 = new Goal();
        goal2.setGrade("7-8");

        Skill skill3 = new Skill("Mustat aukot");

        Exercise exer12 = new Exercise("101b");
        Exercise exer13 = new Exercise("76");
        Exercise exer14 = new Exercise("Katso Cosmos: A Spacetime Odyssey -sarjan seitsemästoista jakso ja kirjoita siitä 200 sanan referaatti.");
        Exercise exer15 = new Exercise("63");

        skill3.addExercise(exer12);
        skill3.addExercise(exer13);
        skill3.addExercise(exer14);
        skill3.addExercise(exer15);

        exerciseService.addExercise(exer12);
        exerciseService.addExercise(exer13);
        exerciseService.addExercise(exer14);
        exerciseService.addExercise(exer15);

        skillService.addSkill(skill3);

        Skill skill4 = new Skill("Valkoiset kääpiöt");

        Exercise exer16 = new Exercise("758");
        Exercise exer17 = new Exercise("827");
        Exercise exer18 = new Exercise("100");
        Exercise exer19 = new Exercise("220");
        Exercise exer20 = new Exercise("12");
        Exercise exer21 = new Exercise("16");
        Exercise exer22 = new Exercise("19");

        skill4.addExercise(exer16);
        skill4.addExercise(exer17);
        skill4.addExercise(exer18);
        skill4.addExercise(exer19);
        skill4.addExercise(exer20);
        skill4.addExercise(exer21);
        skill4.addExercise(exer22);

        exerciseService.addExercise(exer16);
        exerciseService.addExercise(exer17);
        exerciseService.addExercise(exer18);
        exerciseService.addExercise(exer19);
        exerciseService.addExercise(exer20);
        exerciseService.addExercise(exer21);
        exerciseService.addExercise(exer22);

        skillService.addSkill(skill4);

        ArrayList<Skill> skills2 = new ArrayList<>();

        skills2.add(skill4);
        skills2.add(skill3);

        goal2.setSkills(skills2);

        goalService.addGoal(goal2);

        Goal goal3 = new Goal();
        goal3.setGrade("9-10");

        Skill skill5 = new Skill("Astrofysiikka");

        Exercise exer23 = new Exercise("83");
        Exercise exer24 = new Exercise("10");
        Exercise exer25 = new Exercise("Laske auringon massa.");
        Exercise exer26 = new Exercise("893");
        Exercise exer27 = new Exercise("Lue Carl Saganin Kosmos ja kirjoita siitä neljän sivun referaatti.");

        exerciseService.addExercise(exer23);
        exerciseService.addExercise(exer24);
        exerciseService.addExercise(exer25);
        exerciseService.addExercise(exer26);
        exerciseService.addExercise(exer27);

        skill5.addExercise(exer23);
        skill5.addExercise(exer24);
        skill5.addExercise(exer25);
        skill5.addExercise(exer26);
        skill5.addExercise(exer27);

        skillService.addSkill(skill5);

        ArrayList<Skill> skillz = new ArrayList<>();
        skillz.add(skill5);
        goal3.setSkills(skillz);

        goalService.addGoal(goal3);

        ArrayList<Goal> goals = new ArrayList<>();
        goals.add(goal1);
        goals.add(goal2);
        goals.add(goal3);

        course.setGoals(goals);

        added = true;

        courseService.addCourse(course);

    }
}
