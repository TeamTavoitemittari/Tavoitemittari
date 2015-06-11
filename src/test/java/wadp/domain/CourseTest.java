/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wadp.domain;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CourseTest {
    
    
    
   

    /**
     * Test of getName and setName method, of class Course.
     */
    @Test
    public void testGetAndSetName() {
        
        Course kurssi = new Course();
        kurssi.setName("nimi");
        String nimi = kurssi.getName();
        assertEquals(nimi, "nimi");
        // TODO review the generated test code and remove the default call to fail.
        
    }



    /**
     * Test of set and getGoals method, of class Course.
     */
    @Test
    public void testSetandGetGoals() {
        System.out.println("getGoals");
        Course kurssi = new Course();
        GradeLevel level = new GradeLevel();
        level.setGrade("9-10");
        ArrayList<GradeLevel> levels = new ArrayList();
        levels.add(level);
        kurssi.setGradeLevels(levels);
        assertEquals(1, kurssi.getGradeLevels().size());
       
    }

  
    
    
    @Test
    public void testGetAndSetDescription(){
        
        Course kurssi = new Course();
        kurssi.setDescription("descrip");
        assertEquals("descrip", kurssi.getDescription());
        kurssi.setDescription("second");
        assertEquals("second", kurssi.getDescription());
    }
    
}
