/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wadp.service;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wadp.domain.Course;

/**
 *
 * @author mide
 */
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
