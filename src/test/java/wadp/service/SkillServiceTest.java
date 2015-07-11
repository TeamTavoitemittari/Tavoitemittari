/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wadp.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wadp.Application;


import wadp.domain.Skill;

import wadp.repository.SkillRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SkillServiceTest {
    
    @Autowired 
    SkillService service;
   
    @Autowired 
    SkillRepository repo;
   
    @Test
    public void testAddandGetSkills() {
        
        Skill taito1 = new Skill();
        service.addSkill(taito1);
        List<Skill> taidot= service.getSkills();
        assertEquals(taidot.size(), 1);
       
    }
 
}
