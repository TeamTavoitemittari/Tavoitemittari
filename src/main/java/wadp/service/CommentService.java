
package wadp.service;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Comment;
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
    public Comment addComment(Skill skill){
        Comment comment = new Comment(skill);
        return commentRepository.save(comment);
    }
    
    @Transactional
    public void updateComment(Comment comment, String newCommentText){
        Comment databaseComment = commentRepository.findOne(comment.getId());
        databaseComment.setComment(newCommentText);
        commentRepository.save(databaseComment);
    }
}
