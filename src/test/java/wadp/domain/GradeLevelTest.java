package wadp.domain;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


public class GradeLevelTest {

    /**
     * Test of set- and getLevel method, of class GradeLevel.
     */
    @Test
    public void testGetAndSetLevel() {
        
        GradeLevel level = new GradeLevel();
        level.setLevel("5-6");
        
        assertEquals("5-6", level.getLevel());
    }



    /**
     * Test of set and getGoals method, of class GradeLevel.
     */
    @Test
    public void testGetGoals() {
        GradeLevel level2 = new GradeLevel();
        Goal goal1 = new Goal();
        goal1.setName("Tähtikuviot");
        Goal goal2 = new Goal();
        goal2.setName("3. ulottuvuus");
        
        
        ArrayList<Goal> goals = new ArrayList<Goal>();
        goals.add(goal1);
        goals.add(goal2);
        
        level2.setGoals(goals);
        
        assertEquals(2, level2.getGoals().size());
      
        
    }


  
    /**
     * Test of set and getGrade method, of class GradeLevel.
     */
    @Test
    public void testGetGrade() {
      GradeLevel level = new GradeLevel();
        level.setGrade("8-9");
        
        assertEquals("8-9", level.getGrade());
    }

   
    
}
