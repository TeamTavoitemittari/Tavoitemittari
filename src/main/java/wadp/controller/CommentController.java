
package wadp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wadp.domain.Comment;
import wadp.domain.Skill;
import wadp.domain.User;
import wadp.service.CommentService;
import wadp.service.CourseService;
import wadp.service.SkillService;
import wadp.service.UserService;

@Controller
@RequestMapping("/comment")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
    
    
    @RequestMapping(value="/{userId}/{courseId}/{commentId}", method=RequestMethod.POST)
    public String postOrUpdateComment(@PathVariable long userId, @PathVariable long courseId, @PathVariable long commentId, @RequestParam String comment){
        Comment com = commentService.findCommentById(commentId);
        commentService.updateComment(com, comment);
        User user = userService.getAuthenticatedUser();
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
