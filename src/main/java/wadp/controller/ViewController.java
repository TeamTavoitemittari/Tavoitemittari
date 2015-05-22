/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wadp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadp.service.UserService;
import wadp.domain.User;
import wadp.repository.UserRepository;

@Controller
@RequestMapping("/view")
public class ViewController {
@Autowired
private UserService userService;


    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {

     model.addAttribute("users", userService.list());
    
        
        return "view";
        
    }
    
}