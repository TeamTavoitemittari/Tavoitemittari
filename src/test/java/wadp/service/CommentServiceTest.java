
package wadp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wadp.Application;
import wadp.domain.Comment;
import wadp.domain.Course;
import wadp.domain.Skill;
import wadp.domain.User;
import wadp.repository.CommentRepository;
import wadp.repository.CourseRepository;
import wadp.repository.SkillRepository;
import wadp.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CommentServiceTest {
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
       
    @Autowired
    private CourseRepository courseRepository; 

    @Autowired
    private UserRepository userRepository; 
    
    private Course course;
    private User user;
    private Skill skill;
    
    @Before
    public void setUp(){
        skill = new Skill();
        skillRepository.save(skill);
        
        course = new Course();
        course.setDescription("descr");
        course.setName("name");
        user = new User();
        courseRepository.save(course);
        userRepository.save(user);
    }
    
    @Test
    public void addingEmptyCommentWorks(){
        assertTrue(commentRepository.findAll().isEmpty());
        
        commentService.addComment(skill, user, course);
        
        assertEquals(1, commentRepository.findAll().size());
    }
    
    @Test
    public void commentsAreFoundById(){
        Comment comment = commentRepository.save(new Comment(skill, user, course));
        assertEquals(comment, commentService.findCommentById(comment.getId()));
    }
    
    @Test
    public void updatingCommentTextWorks(){
        Comment comment = new Comment(skill,user, course); 
        comment.setComment("comment");
        commentRepository.save(comment);
        
        commentService.updateComment(comment, "comment2");
        assertEquals("comment2", commentRepository.findOne(comment.getId()).getComment());
    }
    
}
