package wadp.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Any request not handled by other controllers is redirected to index
@Controller
@RequestMapping("*")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String showIndex(Model model) {
        
        return "index";
    }
}
