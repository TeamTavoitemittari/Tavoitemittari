
package wadp.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


public class CommentTest {
    
    private Skill skill;
    private Course course;
    private User user;
    
    
    @Before
    public void setUp(){
        skill = new Skill();
        course = new Course();
        course.setName("name");
        user = new User();
    }
    
    @Test
    public void constructorTest(){
        Comment comment = new Comment(skill, user, course);
        
        assertEquals("", comment.getComment());
        assertEquals(skill, comment.getSkill());
    }
    
    @Test
    public void skillSettingWorks(){
        Comment comment = new Comment(skill, user, course);
        Skill skill2 = new Skill();
        comment.setSkill(skill2);
        assertEquals(skill2, comment.getSkill());
    }
    
    @Test
    public void commentSettingWorks(){
        Comment comment = new Comment(skill, user, course); 
        comment.setComment("comment");
        
        assertEquals("comment", comment.getComment());
    }
    
}
