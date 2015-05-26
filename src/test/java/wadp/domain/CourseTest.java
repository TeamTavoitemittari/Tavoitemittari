/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wadp.domain;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author harkonsa
 */
public class CourseTest {
    
    public CourseTest() {
    }
    
   

    /**
     * Test of getName method, of class Course.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Course instance = new Course();
        instance.setName("name");
        String expResult = "name";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setName method, of class Course.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "name";
        Course instance = new Course();
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of getGoals method, of class Course.
     */
    @Test
    public void testGetGoals() {
        System.out.println("getGoals");
        Course instance = new Course();
        List<Goal> expResult = instance.getGoals();
        assertEquals(expResult, instance.getGoals());
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of setGoals method, of class Course.
     */
    @Test
    public void testSetGoals() {
        System.out.println("setGoals");
        List<Goal> goals = null;
        Course instance = new Course();
        instance.setGoals(goals);
        Course expResult = new Course();
        expResult.setGoals(goals);
         
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expResult.getGoals(), instance.getGoals());
    }
    
    @Test
    public void testGetAndSetDescription(){
        System.out.println("setAndGetDescription");
        Course course = new Course();
        course.setDescription("descrip");
        assertEquals("descrip", course.getDescription());
        course.setDescription("second");
        assertEquals("second", course.getDescription());
    }
    
}
