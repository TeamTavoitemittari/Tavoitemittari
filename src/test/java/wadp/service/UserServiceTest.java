/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wadp.service;

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
import wadp.domain.User;
import wadp.repository.UserRepository;



    
    @RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

  
    
    public UserServiceTest() {
    }
        @Autowired 
        UserService service;
        @Autowired 
        UserRepository repo;
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of list method, of class UserService.
     */
    @Test
    public void testList() {
        
        
        service.createUser("matti@meikalainen.com", "salasana", "matti meikalainen", "teacher");
        
        List<User> result = service.list();
       
        assertEquals(result.isEmpty(), false);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createUser method, of class UserService.
     */
    @Test
    public void testCreateUser() {
        service.createUser("jukka@meikalainen.com", "salasana", "jukka meikalainen", "teacher");
        User user = repo.findByEmail("jukka@meikalainen.com");
        String name = "jukka meikalainen";
        assertTrue(user.getName().equals(name));
        
    }
    
        @Test
    public void testCreateUserEmailHasCapitalLetters() {
        service.createUser("Jukka@Meikalainen.com", "salasana", "jukka meikalainen", "teacher");
        User user = repo.findByEmail("jukka@meikalainen.com");
        String name = "jukka meikalainen";
        assertTrue(user.getName().equals(name));
    }
    
          @Test
    public void testCreateUserEmailHasEmptySpace() {
        service.createUser("  Jukka@Salo.com  ", "salasana", "jukka meikalainen", "teacher");
        User user = repo.findByEmail("jukka@salo.com");
        String name = "jukka meikalainen";
        assertTrue(user.getName().equals(name));
        
    }
    
          

    @Test(expected=EmailAlreadyRegisteredException.class)
    public void SameEmailThrowsException() {
       service.createUser("jukka@malo.com", "salasana", "jukka meikalainen", "teacher");
       service.createUser("Jukka@malo.com", "salasana", "jukka meikalainen", "teacher");
    }
    
     @Test(expected=EmailAlreadyRegisteredException.class)
    public void SameEmailWithCapitals() {
       service.createUser("Jukka@Malo.com", "salasana", "jukka meikalainen", "teacher");
       service.createUser("jukka@malo.com", "salasana", "jukka meikalainen", "teacher");
    }
    
      @Test(expected=EmailAlreadyRegisteredException.class)
    public void SameEmailWithEmptySpaces() {
       service.createUser("  jukka@Oalo.com", "salasana", "jukka meikalainen", "teacher");
       service.createUser("jukka@oalo.com", "salasana", "jukka meikalainen", "teacher");
    }
  

    @Test(expected=IllegalArgumentException.class)
    public void cannotCreateUserWithNoEmail() {
        service.createUser("", "salasana", "jukka meikalainen", "teacher");
    }

    @Test(expected=IllegalArgumentException.class)
    public void cannotCreateUserWithNullEmail() {
        service.createUser(null, "salasana", "jukka meikalainen", "teacher");
    }
    
    
    
}