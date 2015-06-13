
package wadp.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class ExerciseTest {
    
    @Test
    public void getterAndSetterTest(){
        Exercise exercise = new Exercise("description");
        assertEquals("description", exercise.getDescription());
        exercise.setDescription("second");
        assertEquals("second", exercise.getDescription());
    }
    
    @Test
    public void TestExerciseToString(){
        
        Exercise exercise2 = new Exercise("kuvaus");
        assertTrue(exercise2.toString().equals("kuvaus"));
        
        
    }
    
}
