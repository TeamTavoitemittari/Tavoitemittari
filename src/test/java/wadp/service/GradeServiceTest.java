package wadp.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wadp.Application;
import wadp.domain.Course;
import wadp.domain.Grade;
import wadp.domain.User;
import wadp.repository.GradeRepository;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GradeServiceTest {

    @Autowired
    private GradeRepository repository;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProgressService progressService;

    private Grade grade;
    private User user;
    private Course course;

    @Before
    public void setUp() {
        user = userService.createUser("kimmo@a.com", "kimmo123", "Kimmo Koululainen", "student");
        course = new Course();
        course.setName("Astronomy");
        course.setDescription("Best course ever");
        courseService.addCourse(course);
        grade = new Grade();
        progressService.createProgressTracker(user, course);
    }

    @Test
    public void giveAndRetrieveGrade() {
        gradeService.giveGrade(user, course, "10");
        assertEquals("10", repository.findByUserAndCourseId(user, course.getId()).getGrade());
        assertEquals("10", gradeService.getStudentCourseGrade(user, course).getGrade());
    }

    @Test
    public void editExistingGrade() {
        gradeService.giveGrade(user, course, "failed");
        assertEquals("failed", repository.findByUserAndCourseId(user, course.getId()).getGrade());
        gradeService.editGrade(user, course, "passed");
        assertEquals("passed", repository.findByUserAndCourseId(user, course.getId()).getGrade());
    }

    @Test
    public void deleteExistingGradeByPostingEmptyField() {
        gradeService.giveGrade(user, course, "10");
        gradeService.editGrade(user, course, " ");
        assertEquals(null, repository.findByUserAndCourseId(user, course.getId()));
    }

    @Test
    public void deleteExistingGrades() {
        gradeService.giveGrade(user, course, "10");
        gradeService.giveGrade(user, course, "8");
        User user2 = userService.createUser("kimmo@gmail.com", "kimmo123", "Kimmo", "student");
        progressService.createProgressTracker(user2, course);
        gradeService.giveGrade(user2, course, "7");
        List<Grade> grades = repository.findAll();
        grades.remove(repository.findByUserAndCourseId(user2, course.getId()));
        gradeService.deleteGrades(grades);
        assertEquals(null, repository.findByUserAndCourseId(user, course.getId()));
        assertEquals("7", repository.findByUserAndCourseId(user2, course.getId()).getGrade());
    }

}
