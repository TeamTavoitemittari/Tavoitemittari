
package wadp.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CourseProgressTrackerTest {
    
    private Skill skill;
    private Course course;
    private User user;
    private CourseProgressTracker tracker;
    
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
        ArrayList<GradeLevel> levels = new ArrayList<GradeLevel>();
        levels.add(level);
        course.setGradeLevels(levels);
        tracker = new CourseProgressTracker(user, course);
    }

    
    @Test
    public void testUpdateSkillStatus() {
        boolean result = tracker.updateSkillStatus(skill, true);
        assertTrue(result);
        result = tracker.updateSkillStatus(new Skill(), true);
        assertFalse(result);
    }

    @Test
    public void testUser() {
        assertTrue(tracker.getUser()==user);
        User user2 = new User();
        tracker.setUser(user2);
        assertTrue(tracker.getUser()==user2);
    }

    @Test
    public void testCourse() {
        assertTrue(tracker.getCourse()==course);
        Course course2 = new Course();
        tracker.setCourse(course2);
        assertTrue(tracker.getCourse()==course2);
    }

    @Test
    public void testGradeLevels() {
        assertEquals(tracker.getGradeLevels().size(), 1);
        tracker.setGradeLevels(new HashMap<GradeLevel, GradeProgressTracker>());
        assertEquals(tracker.getGradeLevels().size(), 0);
    }

    
}
