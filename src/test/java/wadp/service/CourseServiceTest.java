
package wadp.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wadp.Application;
import wadp.domain.Course;
import wadp.domain.Goal;
import wadp.domain.GradeLevel;
import wadp.repository.CourseRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CourseServiceTest {
    
    @Autowired
    private CourseService courseService;
   
    @Autowired
    private CourseRepository courseRepository;
    
    private Course course;

    @Test
    public void testGetCourses() {
        course = new Course();
        course.setName("todari");
        course.setDescription("todari");
        courseRepository.save(course);
        assertEquals("todari", courseService.getCourses().get(0).getName());
        assertEquals(1, courseService.getCourses().size());
    }

    @Test
    public void testAddCourse() {
        course = new Course();
        course.setName("todari");
        course.setDescription("todari2");
        courseService.addCourse(course);
        
        assertEquals("todari", courseRepository.findAll().get(0).getName());
        assertEquals(1, courseRepository.findAll().size());
    }
    
    @Test
    public void getCourseById() {
        course = new Course();
        course.setName("kurssi1");
        course.setDescription("kurssi taas");
        courseService.addCourse(course);
        Long id = course.getId();
        Course samecourse = courseService.getCourseById(id);
        
        
        assertTrue(samecourse.getName().equals(course.getName()));
        
    }

    @Test
    public void updateCourseWorks(){
        course = new Course();
        List<GradeLevel> gradeLevels = new ArrayList<GradeLevel>();
        GradeLevel l1 = new GradeLevel();
        GradeLevel l2 = new GradeLevel();
        GradeLevel l3 = new GradeLevel();
 
        List<Goal> goals = new ArrayList<Goal>();
        Goal x = new Goal();
        x.setName("xx");
        goals.add(x);
        l1.setGoals(goals);
        
        List<Goal> goals2 = new ArrayList<Goal>();
        Goal x2 = new Goal();
        x2.setName("xx");
        goals2.add(x2);
        l2.setGoals(goals2);
        
        List<Goal> goals3 = new ArrayList<Goal>();
        Goal x3 = new Goal();
        x3.setName("xx");
        goals3.add(x3);
        l3.setGoals(goals3);
        
        gradeLevels.add(l1);
        gradeLevels.add(l2);
        gradeLevels.add(l3);
        
        course.setGradeLevels(gradeLevels);
        course.setName("test");
        course.setDescription("descr");
        long oldCourseId = courseRepository.save(course).getId();

        assertEquals("test", courseService.getCourseById(oldCourseId).getName());

        Course course2 = new Course();

        List<GradeLevel> gradeLevels2 = new ArrayList<GradeLevel>();
        GradeLevel l4 = new GradeLevel();
        GradeLevel l5 = new GradeLevel();
        GradeLevel l6 = new GradeLevel();
        
        List<Goal> goals4 = new ArrayList<Goal>();
        Goal x4 = new Goal();
        x4.setName("xx");
        goals4.add(x4);
        l4.setGoals(goals4);
        
        List<Goal> goals5 = new ArrayList<Goal>();
        Goal x5 = new Goal();
        x5.setName("xx");
        goals5.add(x5);
        l5.setGoals(goals5);
        
        List<Goal> goals6 = new ArrayList<Goal>();
        Goal x6 = new Goal();
        x6.setName("xx");
        goals6.add(x6);
        l6.setGoals(goals6);
        
        gradeLevels2.add(l4);
        gradeLevels2.add(l5);
        gradeLevels2.add(l6);
        course2.setGradeLevels(gradeLevels2);
        course2.setName("newname");
        course2.setDescription("newdescr");

        courseService.updateCourse(course2, oldCourseId);

        assertEquals("newname", courseService.getCourseById(oldCourseId).getName());
        assertEquals("newdescr", courseService.getCourseById(oldCourseId).getDescription());
    }
}
