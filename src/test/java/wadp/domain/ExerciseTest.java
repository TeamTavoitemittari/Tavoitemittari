
package wadp.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ExerciseTest {
    
    @Test
    public void getterAndSetterTest(){
        Exercise exercise = new Exercise("description");
        assertEquals("description", exercise.getDescription());
        exercise.setDescription("second");
        assertEquals("second", exercise.getDescription());
    }
    
}
