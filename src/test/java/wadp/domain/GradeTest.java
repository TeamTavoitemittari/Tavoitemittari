package wadp.domain;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GradeTest {

    private User user;
    private Grade grade;

    @Before
    public void setUp(){
        this.user = new User();
        this.grade = new Grade();
    }

    @Test
    public void getAndSetUser(){
        grade.setUser(user);
        assertEquals(user, grade.getUser());
        User user2 = new User();
        grade.setUser(user2);
        assertEquals(user2, grade.getUser());
    }

    @Test
    public void getAndSetCourseName(){
        grade.setCourseName("Astronomy");
        assertEquals("Astronomy", grade.getCourseName());
    }

    @Test
    public void getAndSetCourseId(){
        Long value = new Long(1);
        grade.setCourseId(value);
        assertEquals(value, grade.getCourseId());
    }

    @Test
    public void getAndSetGrade(){
        grade.setGrade("10+");
        assertEquals("10+", grade.getGrade());
    }

}
