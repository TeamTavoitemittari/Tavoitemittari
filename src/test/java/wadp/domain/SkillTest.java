
package wadp.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class SkillTest {
    

    /**
     * Test of SetExercise and GetExercise method, of class Skill.
     */
    @Test
    public void testGetandSetExercise() {
        System.out.println("setExercise");
        String Exercise = "Calculate the mass of the sun";
        Skill skill = new Skill();
        skill.setExercise(Exercise);
        assertEquals(Exercise, skill.getExercise());
    }


    
    /**
     * Test of description getters and setters, of class Skill.
     */
    @Test
    public void descriptionTest(){
        Skill skill = new Skill();
        skill.setDescription("second");
        assertEquals("second", skill.getDescription());
      
    }
}
