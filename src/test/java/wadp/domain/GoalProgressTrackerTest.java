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
        boolean found = tracker.updateSkillStatus(skill, Status.STUDENT_CONFIRMED);
        assertTrue(found);
        found = tracker.updateSkillStatus(new Skill(), Status.STUDENT_CONFIRMED);
        assertFalse(found);
    }

    @Test
    public void testReady() {
        Status status = tracker.getReady();
        assertEquals(Status.UNCONFIRMED, status);
        tracker.updateSkillStatus(skill, Status.STUDENT_CONFIRMED);
        assertEquals(Status.STUDENT_CONFIRMED, tracker.getReady());
    }


    @Test
    public void testSkills() {
        assertEquals(tracker.getSkills().size(), 1);
        tracker.setSkills(new HashMap<Skill, Status>());
        assertEquals(tracker.getSkills().size(), 0);
    }

}
