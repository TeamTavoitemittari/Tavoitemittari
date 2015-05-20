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
public class GoalTest {
    
    public GoalTest() {
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
     * Test of getGrade method, of class Goal.
     */
    @Test
    public void testGetGrade() {
        System.out.println("getGrade");
        Goal instance = new Goal();
        instance.setGrade("testi");
        String expResult = "testi";
        String result = instance.getGrade();
        assertEquals(expResult, result);
       
       
    }

    /**
     * Test of setGrade method, of class Goal.
     */
    @Test
    public void testSetGrade() {
        System.out.println("setGrade");
        String name = "testi";
        Goal instance = new Goal();
        instance.setGrade("testi");
        String result = instance.getGrade();
        String expResult = "testi";        
        assertEquals(expResult, result);
        
      
    }

    /**
     * Test of getSkills method, of class Goal.
     */
    @Test
    public void testGetSkills() {
        System.out.println("getSkills");
        Goal instance = new Goal();
        List<Skill> expResult = null;
        List<Skill> result = instance.getSkills();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setSkills method, of class Goal.
     */
    @Test
    public void testSetSkills() {
        System.out.println("setSkills");
        List<Skill> skills = null;
        Goal instance = new Goal();
        instance.setSkills(skills);
        Goal expResult = new Goal();
        expResult.setSkills(skills);
        
        assertEquals(expResult.getSkills(), instance.getSkills());
      
    }
    
}
