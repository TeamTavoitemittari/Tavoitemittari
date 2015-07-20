
package wadp.domain;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;


public class GradeProgressTrackerTest {

    private GradeProgressTracker tracker;
    private Skill skill;
    private Course course;
    private User user;

    @Before
    public void setUp() {
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
        assertTrue(tracker.getReady());
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

    @Test
    public void testGoals() {
        HashMap<Goal, GoalProgressTracker> trackers = new HashMap<Goal, GoalProgressTracker>();
        trackers.put(new Goal(), new GoalProgressTracker());
        tracker.setGoals(trackers);
        assertEquals(1, tracker.getGoals().size());
    }


}
