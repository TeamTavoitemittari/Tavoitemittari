
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
