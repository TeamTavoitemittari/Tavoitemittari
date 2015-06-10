
package wadp.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wadp.Application;
import wadp.domain.User;
import wadp.repository.UserRepository;



    
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceTest {

  
    @Autowired 
    UserService service;
   
    @Autowired 
    UserRepository repo;
    
    
     private User loggedInUser;

    @Before
    public void setUp() {

        loggedInUser = service.createUser("matti2@meikalainen.com", "salasana", "matti meikalainen", "teacher");
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(loggedInUser.getUserRole()));
        SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(loggedInUser.getEmail(), loggedInUser.getPassword(), grantedAuths));

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testList() {
        
        service.createUser("matti@meikalainen.com", "salasana", "matti meikalainen", "teacher");
        List<User> result = service.list();
        assertEquals(result.isEmpty(), false);
        // TODO review the generated test code and remove the default call to fail.
        
    }

   
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
    
    @Test
    public void testAuthenticationSuccess() {
        service.createUser("toni@meikalainen.com", "salasana", "toni meikalainen", "teacher");
        User authenticated = service.authenticate("toni@meikalainen.com", "salasana");
        
        assertTrue(authenticated.getEmail().equals("toni@meikalainen.com"));
        
    }
    
    @Test(expected=AuthenticationException.class)
    public void authenticationFailureWhenUserDoesntExist() {
        service.authenticate("anonymous@anonymous.com", "anonymous");
    }

    @Test(expected=AuthenticationException.class)
    public void authenticateThrowsIfPasswordIsIncorrect() {
        service.createUser("irving@meikalainen.com", "salasana", "irving meikalainen", "teacher");
        service.authenticate("irving@meikalainen.com", "salainensana");
    }
    
    @Test
    public void getAuthenticateduserSuccess() {
     
         User user = service.getAuthenticatedUser();
         assertTrue(user.getEmail().equals("matti2@meikalainen.com"));
       
   }
    @Test
    public void PasswordChangeSuccess() {
          
          service.ChangePassword("uusisalasana");
          User user = service.getAuthenticatedUser();
          assertTrue(user.passwordEquals("uusisalasana"));
    
       
   }
    
}
