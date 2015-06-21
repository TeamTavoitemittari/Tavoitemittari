
package wadp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comment")
public class CommentController {
    
    @RequestMapping(value="/{id}", method=RequestMethod.POST)
    public void postComment(){
        //TODO: handle the creation or updating of a comment
    }
    
    
}
