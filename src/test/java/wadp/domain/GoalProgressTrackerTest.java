package wadp.domain;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GoalProgressTrackerTest {

    private GoalProgressTracker tracker;
    private Skill skill;
    private Course course;
    private User user;

    @Before
    public void setUp() {
        Goal goal = new Goal();
        skill = new Skill();
        ArrayList<Skill> skills = new ArrayList<Skill>();
        skills.add(skill);
        goal.setSkills(skills);
        course = new Course();
        course.setName("name");
        user = new User();
        tracker = new GoalProgressTracker(goal, user, course);
      

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
        tracker.setReady(Status.TEACHER_CONFIRMED);
        assertEquals(Status.TEACHER_CONFIRMED, tracker.getReady());
    }


    @Test
    public void testSkills() {
        assertEquals(tracker.getSkills().size(), 1);
        tracker.setSkills(new HashMap<Skill, Status>());
        assertEquals(tracker.getSkills().size(), 0);
    }

    @Test
    public void testUser() {
        tracker.setUser(user);
        assertEquals(user, tracker.getUser());
    }

    @Test
    public void testCourse() {
        tracker.setCourse(course);
        assertEquals(course, tracker.getCourse());
    }

}
