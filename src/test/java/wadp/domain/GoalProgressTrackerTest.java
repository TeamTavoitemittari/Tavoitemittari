package wadp.domain;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GoalProgressTrackerTest {

    private GoalProgressTracker tracker;
    private Skill skill;

    @Before
    public void setUp() {
        Goal goal = new Goal();
        skill = new Skill();
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(skill);
        goal.setSkills(skills);
        tracker = new GoalProgressTracker(goal);

    }

    @Test
    public void testUpdateSkillStatus() {
        boolean found = tracker.updateSkillStatus(skill, true);
        assertTrue(found);
        found = tracker.updateSkillStatus(new Skill(), true);
        assertFalse(found);
    }

    @Test
    public void testReady() {
        boolean status = tracker.getReady();
        assertFalse(status);
        tracker.updateSkillStatus(skill, true);
        assertTrue(tracker.getReady());
    }


    @Test
    public void testSkills() {
        assertEquals(tracker.getSkills().size(), 1);
        tracker.setSkills(new HashMap<Skill, Boolean>());
        assertEquals(tracker.getSkills().size(), 0);
    }

}
