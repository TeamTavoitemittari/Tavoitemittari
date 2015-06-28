
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
    
    
    @RequestMapping(value="/{courseId}/{skillId}", method=RequestMethod.POST)
    public String postOrUpdateComment(@PathVariable long courseId, @PathVariable long skillId, @RequestParam String comment){
        User user = userService.getAuthenticatedUser();
        Skill skill = skillService.findSkill(skillId);
        Comment com = commentService.findComment(user, skill);
        commentService.updateComment(com, comment);
        return "redirect:/course/"+courseId;
    }
    
}
