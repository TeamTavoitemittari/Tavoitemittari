
package wadp.domain;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class GradeProgressTrackerTest {
    
    private GradeProgressTracker tracker;
    private Skill skill;
    private Course course;
    private User user;
    
    @Before
    public void setUp(){
        course = new Course();
        course.setName("name");
        user = new User();
        
        GradeLevel level = new GradeLevel();
        Goal goal = new Goal();
        skill = new Skill();
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(skill);
        goal.setSkills(skills);
        ArrayList<Goal> goals = new ArrayList<Goal>();
        goals.add(goal);
        level.setGoals(goals);
        tracker = new GradeProgressTracker(level, user, course);
    }

    @Test
    public void testUpdateSkillStatus() {
        boolean result = tracker.updateSkillStatus(skill, Status.STUDENT_CONFIRMED);
        assertEquals(true, result);
        
        result = tracker.updateSkillStatus(new Skill(), Status.UNCONFIRMED);
        assertEquals(false, result);
    }

   
    @Test
    public void testIsReady() {
        tracker.setReady(true);
        assertTrue(tracker.isReady());
    }


    @Test
    public void testGoals() {
        assertEquals(tracker.getGoals().size(), 1);
    }
    
}
