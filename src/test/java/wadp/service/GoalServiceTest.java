package wadp.service;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wadp.Application;
import wadp.domain.Goal;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GoalServiceTest {
    
    @Autowired 
    private GoalService service;
   
    @Test
    public void testAddandGetGoals() {

        Goal tavoite1 = new Goal();
        tavoite1.setName("todari");
        service.addGoal(tavoite1);
        List<Goal> tavoitteet = service.getGoals();
        assertEquals(tavoitteet.size(), 1);
   
    }


}