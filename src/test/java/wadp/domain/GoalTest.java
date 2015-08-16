
package wadp.domain;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


public class GoalTest {
    
   
    /**
     * Test of getGrade and setGrade method, of class Goal.
     */
    @Test
    public void testSetAndGetName() {
        System.out.println("getGrade");
        Goal tavoite = new Goal();
        tavoite.setName("testi");
        String expResult = "testi";
        String result = tavoite.getName();
        assertEquals(expResult, result);
       
       
    }


    /**
     * Test of getSkills and setSkills, of class Goal.
     */
    @Test
    public void testSetAndGetSkills() {
        System.out.println("getSkills");
        
        Goal tavoite = new Goal();        
        Skill taito = new Skill("Taito", "harjoitus");
        
        
        ArrayList<Skill> taidot = new ArrayList();
        taidot.add(taito);
        tavoite.setSkills(taidot);
        assertEquals(1, tavoite.getSkills().size());
        
       
    }


    
}
