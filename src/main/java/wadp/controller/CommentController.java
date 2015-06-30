
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
    
    @Autowired
    private SkillService skillService;
    
    
    @RequestMapping(value="/{courseId}/{commentId}", method=RequestMethod.POST)
    public String postOrUpdateComment(@PathVariable long courseId, @PathVariable long commentId, @RequestParam String comment){
        Comment com = commentService.findCommentById(commentId);
        commentService.updateComment(com, comment);
        return "redirect:/course/"+courseId+"#comments";
    }
    
}
