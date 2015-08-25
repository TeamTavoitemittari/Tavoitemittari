package wadp.domain;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void testPasswordEqualsWhenRightpassword() {
       
        String plaintextPassword = "password";
        User instance = new User();
        instance.setPassword("password");
        boolean result = instance.passwordEquals(plaintextPassword);
        assertEquals(true, result);
       
    }
    
      @Test
    public void testPasswordEqualsWhenWrongPassword() {
        String plaintextPassword = "password2";
        User instance = new User();
        instance.setPassword("password");
        boolean result = instance.passwordEquals(plaintextPassword);
        assertEquals(false, result);
       
    }

    /**
     * Test of set and getEmail method, of class User.
     */
    @Test
    public void testSetandGetEmail() {
        
        User instance = new User();
        instance.setEmail("email@email.com");
        String expResult = "email@email.com";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        
    }


    @Test
    public void testGetPasswordReturnsCryptedPassword() {
        System.out.println("getPassword");
        User instance = new User();
        instance.setPassword("password");
        String expResult = "password";
        String result = instance.getPassword();
        assertNotEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
     
    }

    /**
     * Test of setName and GetName method, of class User.
     */
    @Test
    public void testSetName() {
        User user = new User();
        user.setName("nimi");
        assertEquals("nimi", user.getName());
      
    }    

    /**
     * Test of SetCourses and  getCourses method, of class User.
     */
    @Test
    public void testGetCourses() {
        
        User user = new User();
        Course kurssi = new Course();
        ArrayList<Course> kurssit = new ArrayList<>();
        kurssit.add(kurssi);
        user.setCourses(kurssit);
        
        assertEquals(1, user.getCourses().size());
        // TODO review the generated test code and remove the default call to fail.
        
    }

  
    /**
     * Test of set and getUserRole method, of class User.
     */
    @Test
    public void testSetAndGetUserRole() {
        User user = new User();
        user.setUserRole("teacher");
        
        assertEquals("teacher", user.getUserRole());
        // TODO review the generated test code and remove the default call to fail.
        
    }

    @Test
    public void testSetAndGetSchoolClass() {
        User user = new User();
        user.setSchoolClass("5B");

        assertEquals("5B", user.getSchoolClass());
    }

    @Test
    public void testSchoolClassDefault() {
        User user = new User();
        assertEquals("00", user.getSchoolClass());
    }

}
