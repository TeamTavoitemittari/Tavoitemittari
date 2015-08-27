
package wadp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Comment;
import wadp.domain.Course;
import wadp.domain.Skill;
import wadp.domain.User;
import wadp.repository.CommentRepository;

/**
 * Service for comment objects.
 */
@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;


    /**
     * Empties a specific comment
     * @param id comment id
     */
    @Transactional
    public void cleanCommentById(long id){
        
        Comment comment  = commentRepository.findOne(id);
        comment.setComment("");
        commentRepository.save(comment);
    }

    public Comment findCommentById(long id){
        return commentRepository.findOne(id);
    }

    /**
     * Adds a new comment object to the database.
     * @param skill the skill to which the comment is related
     * @param user the user which the comment is related to
     * @param course the course to which the comment is related
     * @return the created comment object
     */
    @Transactional
    public Comment addComment(Skill skill, User user, Course course){
        Comment comment = new Comment(skill, user, course);
        return commentRepository.save(comment);
    }

    /**
     * Updates an existing comment
     * @param comment the object of the existing comment
     * @param newCommentText the new comment (includes the relevant parts of the old comment)
     */
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
    
    public List<Comment> getCommentsByUser(User user){
        List<Comment> comments = commentRepository.findByUser(user);
        return comments;
    }

    /**
     * Deletes all comments from the database that are included in the param list
     * @param comments to be deleted
     */
    @Transactional
    public void deleteComments(List<Comment> comments){
        commentRepository.delete(comments);
    }
    
}
