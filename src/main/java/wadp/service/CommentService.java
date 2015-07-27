
package wadp.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Comment;
import wadp.domain.Course;
import wadp.domain.Skill;
import wadp.domain.User;
import wadp.repository.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
   
    
    public Comment findCommentById(long id){
        return commentRepository.findOne(id);
    }
    
    @Transactional
    public Comment addComment(Skill skill, User user, Course course){
        Comment comment = new Comment(skill, user, course);
        return commentRepository.save(comment);
    }
    
    @Transactional
    public void updateComment(Comment comment, String newCommentText){
        Comment databaseComment = commentRepository.findOne(comment.getId());
        databaseComment.setComment(newCommentText);
        commentRepository.save(databaseComment);
    }
    public List<Comment> getCommentsByCourse(Course course){
        List<Comment> comments = commentRepository.findByCourse(course);
        return comments;
    }
    
    public List<Comment> getCommentsByUserAndCourse(User user, Course course){
        List<Comment> comments = commentRepository.findByUserAndCourse(user, course);
        return comments;
    }
        
    @Transactional
    public void deleteComments(List<Comment> comments){
        commentRepository.delete(comments);
    }
    
}
