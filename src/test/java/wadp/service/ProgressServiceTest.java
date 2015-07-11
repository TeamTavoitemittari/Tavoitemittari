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
    public void testGetProgressByCourse(){
        coProgressRepository.save(tracker);
        assertTrue(progressService.getProgressByCourse(course).size() == 1);
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


}
