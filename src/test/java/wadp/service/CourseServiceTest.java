
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
        courseRepository.save(course);
        assertEquals("todari", courseService.getCourses().get(0).getName());
        assertEquals(1, courseService.getCourses().size());
    }

    @Test
    public void testAddCourse() {
        course = new Course();
        course.setName("todari");
        courseService.addCourse(course);
        
        assertEquals("todari", courseRepository.findAll().get(0).getName());
        assertEquals(1, courseRepository.findAll().size());
    }
    
    @Test
    public void GetCourseById() {
        course = new Course();
        course.setName("kurssi1");
        courseService.addCourse(course);
        Long id = course.getId();
        Course samecourse = courseService.getCourseById(id);
        
        
        assertTrue(samecourse.getName().equals(course.getName()));
        
    }
}
