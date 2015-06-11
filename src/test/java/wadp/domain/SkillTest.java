
package wadp.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SkillTest {
    

    /**
     * Test of addExercise method, of class Skill.
     */
    @Test
    public void testAddExercise() {
        System.out.println("addExercise");
        Exercise exercise = new Exercise("Calculate the mass of the sun");
        Skill skill = new Skill("Planetary calculation");
        skill.addExercise(exercise);
        assertEquals(1, skill.getExercises().size());
    }

    /**
     * Test of getExercises method, of class Skill.
     */
    @Test
    public void testGetExercises() {
        System.out.println("getExercises");
        Skill skill = new Skill("Planetary calculation");
        assertEquals(0, skill.getExercises().size());
        skill.addExercise(new Exercise("Calculate the mass of the sun"));
        assertEquals(1, skill.getExercises().size());
    }
    
    /**
     * Test of description getters and setters, of class Skill.
     */
    @Test
    public void nameTest(){
        Skill skill = new Skill("descr");
        assertEquals("descr", skill.getName());
        skill.setName("second");
        assertEquals("second", skill.getName());
    }
}
