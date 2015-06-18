
package wadp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import wadp.service.ProgressService;


@Controller
@RequestMapping("/progress")
public class ProgressController {
    
    @Autowired
    private ProgressService progressService;
    
    
}
