
package wadp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import wadp.domain.Comment;
import wadp.domain.User;
import wadp.service.CommentService;
import wadp.service.UserService;

@Controller
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
    
    
    @PreAuthorize("hasAuthority('teacher')")
    @RequestMapping(value ="/{userId}/{courseId}/{commentId}/clean", method = RequestMethod.POST)
    public String deleteComment(RedirectAttributes redirectAttributes, @PathVariable long userId, @PathVariable long courseId, @PathVariable long commentId) {
         commentService.cleanCommentById(commentId);
      
         return "redirect:/course/"+courseId+"/"+userId+"#comments";
    }
    
    
    @RequestMapping(value="/{userId}/{courseId}/{commentId}", method=RequestMethod.POST)
    public String postOrUpdateComment(@PathVariable long userId, @PathVariable long courseId, @PathVariable long commentId, @RequestParam String comment){
        Comment com = commentService.findCommentById(commentId);
        String old = com.getComment();
        User user = userService.getAuthenticatedUser();
        String newComment=("("+new SimpleDateFormat("dd.MM.yy ':' HH:mm").format(new Date())+") " + user.getName()+": " +comment+"\n"+old);
        commentService.updateComment(com, newComment);
        if("teacher".equals(user.getUserRole())){
            return "redirect:/course/"+courseId+"/"+userId+"#comments";
        }
        return "redirect:/course/"+courseId+"#comments";
    }

    @RequestMapping(value="/{courseId}/cancel")
    public String cancelCommentUpdatingAsStudent(@PathVariable long courseId){
        return "redirect:/course/"+courseId+"#comments";
    }


    @RequestMapping(value="/{courseId}/{studentId}/cancel", method=RequestMethod.GET)
    public String cancelCommentUpdatingAsTeacher(@PathVariable long courseId, @PathVariable long studentId){
        return "redirect:/course/"+courseId+"/"+studentId+"#comments";
    }
    
}
