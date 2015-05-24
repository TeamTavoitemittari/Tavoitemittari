package wadp.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.service.UserService;

// Any request not handled by other controllers is redirected to index
@Controller
@RequestMapping("*")
public class IndexController {
    
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String showIndex(Model model) {
        
        return "index";
        
    }
    
     @RequestMapping(value = "/prototype", method = RequestMethod.GET)
    public String showPrototype(Model model) {
        
        return "courses";
        
    }
    
    
    
      @RequestMapping(value = "/welcome", method = RequestMethod.GET)
     public String showWelcomePage(Model model) {
        model.addAttribute("users", userService.list());
        return "welcome";
    }
}
