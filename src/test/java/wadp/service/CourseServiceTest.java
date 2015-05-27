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
import wadp.domain.Course;
import wadp.domain.Goal;
import wadp.repository.CourseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CourseServiceTest {
    
 

    
    @Autowired 
    CourseService service;
   
    @Autowired 
    CourseRepository repo;
   
  
    
   
    @Test
    public void testAddandGetCourses() {
        
       Course kurssi1 = new Course();
       Course kurssi2 = new Course();
       service.addCourse(kurssi1);
       service.addCourse(kurssi2);
       List<Course> Allcourses = service.getCourses();      
       assertEquals(Allcourses.size(), 2);
       
        
    }
 

    
}
