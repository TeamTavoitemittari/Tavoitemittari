package wadp.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import static org.junit.Assert.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wadp.Application;
import wadp.domain.*;
import wadp.repository.*;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProgressServiceTest {

    @Autowired
    private ProgressService progressService;

    @Autowired
    private CourseProgressRepository coProgressRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private GoalProgressRepository goProgressRepository;

    @Autowired
    private GradeProgressRepository grProgressRepository;

    @Autowired
    private UserRepository userRepository;

    private CourseProgressTracker tracker;
    private User user;
    private Course course;

    @Before
    public void setUp(){
        tracker = new CourseProgressTracker();
        course = new Course();
        course.setDescription("descr");
        course.setName("name");
        user = new User();
        tracker.setCourse(course);
        tracker.setUser(user);
        courseRepository.save(course);
        userRepository.save(user);
    }

    @Test
    public void testGetProgress(){
        coProgressRepository.save(tracker);
        assertEquals(tracker, progressService.getProgress(user, course));
    }

    @Test
    public void testgetCourseProgressTrackersByCourse(){
        coProgressRepository.save(tracker);
        assertTrue(progressService.getCourseProgressTrackersByCourse(course).size() == 1);
    }

    @Test
    public void testCreateProgressTracker(){
        assertEquals(course, progressService.createProgressTracker(user, course).getCourse());
    }

    @Test
    public void progressSaversSave(){
        progressService.saveCourseTracker(new CourseProgressTracker());
        progressService.saveGoalTracker(new GoalProgressTracker());
        progressService.saveGradeTracker(new GradeProgressTracker());

        assertEquals(1, coProgressRepository.findAll().size());
        assertEquals(1, grProgressRepository.findAll().size());
        assertEquals(1, goProgressRepository.findAll().size());
    }

    @Test
    public void swapSkillStatusWorks(){

        CourseProgressTracker tracker2 = new CourseProgressTracker(new User(), createCourse());
        Course trackerCourse = tracker2.getCourse();
        GradeLevel gradeLevel = trackerCourse.getGradeLevels().get(0);
        Goal goal = gradeLevel.getGoals().get(0);
        Skill skill = goal.getSkills().get(0);
        assertEquals(Status.UNCONFIRMED, tracker2.getGradeLevels().get(gradeLevel).getGoals().get(goal).getSkills().get(skill));
        progressService.swapSkillsStatus(tracker2, gradeLevel, goal, skill);
        assertEquals(Status.STUDENT_CONFIRMED, tracker2.getGradeLevels().get(gradeLevel).getGoals().get(goal).getSkills().get(skill));
        progressService.swapSkillsStatus(tracker2, gradeLevel, goal, skill);
        assertEquals(Status.UNCONFIRMED, tracker2.getGradeLevels().get(gradeLevel).getGoals().get(goal).getSkills().get(skill));

    }

    @Test
    public void swapSkillStatusAsTeacherTest(){
        CourseProgressTracker tracker2 = new CourseProgressTracker(new User(), createCourse());
        Course trackerCourse = tracker2.getCourse();
        GradeLevel gradeLevel = trackerCourse.getGradeLevels().get(0);
        Goal goal = gradeLevel.getGoals().get(0);
        Skill skill = goal.getSkills().get(0);
        assertEquals(Status.UNCONFIRMED, tracker2.getGradeLevels().get(gradeLevel).getGoals().get(goal).getSkills().get(skill));
        progressService.swapSkillStatusAsTeacher(tracker2, gradeLevel, goal, skill);
        assertEquals(Status.TEACHER_CONFIRMED, tracker2.getGradeLevels().get(gradeLevel).getGoals().get(goal).getSkills().get(skill));
        progressService.swapSkillStatusAsTeacher(tracker2, gradeLevel, goal, skill);
        assertEquals(Status.UNCONFIRMED, tracker2.getGradeLevels().get(gradeLevel).getGoals().get(goal).getSkills().get(skill));
    }

    public Course createCourse(){
        Skill skill = new Skill();
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(skill);
        Goal goal = new Goal();
        goal.setSkills(skills);
        ArrayList<Goal> goals = new ArrayList<Goal>();
        goals.add(goal);
        GradeLevel level = new GradeLevel();
        level.setGoals(goals);
        ArrayList<GradeLevel> levels = new ArrayList<GradeLevel>();
        levels.add(level);
        Course course = new Course();
        course.setGradeLevels(levels);
        return course;
    }
}
