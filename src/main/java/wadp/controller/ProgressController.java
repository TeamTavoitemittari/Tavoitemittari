
package wadp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.service.ProgressService;


@Controller
@RequestMapping("/progress")
public class ProgressController {
    
    @Autowired
    private ProgressService progressService;
    
    @RequestMapping(value = "/{skillId}", method = RequestMethod.POST)
    public void updateSkillStatus(@PathVariable String skillId){

    }
    
    
}
