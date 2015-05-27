
package wadp.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wadp.domain.Course;



public class CourseServiceTest {

    public CourseServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCourses method, of class CourseService.
     */
    @Test
    public void testGetCourses() {
        System.out.println("getCourses");
        CourseService instance = new CourseService();
        List<Course> expResult = null;
        List<Course> result = instance.getCourses();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCourse method, of class CourseService.
     */
    @Test
    public void testAddCourse() {
        System.out.println("addCourse");
        Course course = null;
        CourseService instance = new CourseService();
        instance.addCourse(course);
    }
}
