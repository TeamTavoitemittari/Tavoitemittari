package integrationtest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class CreateCourseTest {
    
    private HtmlUnitDriver driver;
    private WebElement element;
    private String name;

    
    public CreateCourseTest() {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/index");
        
    }
    
    
    @Before
    public void setUp() {
        name = "Kurssi";
    }

  /*  @Test
    public void teacherCanAccessNewCourseCreationPageButCannotCreateCourseSinceGoalsMustBeSet() {
        createUser("ope", "Opettaja21", "teacher");
        login("ope@gmail.com", "Opettaja21");
        getCourseCreationPage();
        assertTrue(driver.getPageSource().contains("Lisää kurssi"));
        createCourse(name, "Tällä kurssila käsitellään jotain");
        assertTrue(driver.getPageSource().contains("Tavoite ei saa olla tyhjä."));
    }*/
//    @Test
//    public void teacherCanCreateaNewCourse() {
//        createUser("ope", "Opettaja21", "teacher");
//        login("ope@gmail.com", "Opettaja21");
//        getCourseCreationPage();
//        assertTrue(driver.getPageSource().contains("Lisää kurssi"));
//        createCourse(name, "Tällä kurssila käsitellään jotain");
//        assertTrue(driver.getPageSource().contains(name));
//        assertTrue(driver.getPageSource().contains("Tällä kurssila käsitellään jotain"));
//    }
    
    @Test
    public void studentCannotCreateaNewCourse() {
        createUser("oppilas", "Oppilas21", "student");
        login("oppilas@gmail.com", "Oppilas21");
        assertFalse(driver.getPageSource().contains("Hallinnointi"));
        try {
            
            getCourseCreationPage(); 
            assertFalse(driver.getPageSource().contains("Lisää kurssi"));
            createCourse("Jokulogia", "Tällä kursilla käsitellään vielä enemmän jotain");
        }
        catch(Exception e) {
            
        }
        assertFalse(driver.getPageSource().contains("Jokulogia"));
    }
    
    @Test
    public void studentCannotBypassRoleChuckByGoingStraightToCourseCreation() {
        createUser("oppilas", "Oppilas21", "student");
        login("oppilas@gmail.com", "Oppilas21");
        try {
            driver.get("http://localhost:8080/courses");
            assertFalse(driver.getPageSource().contains("Hallinnointi"));
            assertFalse(driver.getPageSource().contains("Lisää kurssi"));
            createCourse("Jokulogia2", "Tällä kursilla käsitellään vielä enemmän jotain");
            
        }
        catch(Exception e) {
            
        }
        assertFalse(driver.getPageSource().contains("Jokulogia2"));
    }
    
    private void login(String email, String password) {
       
        driver.get("http://localhost:8080/index");
        element = driver.findElementByName("email");
        element.sendKeys(email);
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.click();
    }

    private void getCourseCreationPage() {
        element = driver.findElement(By.id("hallinnointi-toggle-menu"));
        element.click();
        element = driver.findElement(By.id("hdd-a"));
        element.click();
        driver.getPageSource();
    }

    private void createCourse(String name, String description) {
        element = driver.findElement(By.id("name"));
        element.sendKeys(name);
        element = driver.findElement(By.id("description"));
        element.sendKeys(description);
        element = driver.findElement(By.xpath("//button[contains(.,'Lisää')]"));
        element.submit();
    }

    private void createUser(String mailName, String password, String role) {
        driver.get("http://localhost:8080/index");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"));
        element.click();
        element = driver.findElement(By.id("name"));
        element.sendKeys(mailName);
        element = driver.findElementByName("email");
        element.sendKeys(mailName + "@gmail.com");
        element = driver.findElementByName("confirmemail");
        element.sendKeys(mailName + "@gmail.com");
        Select select = new Select(driver.findElement(By.name("userRole")));
        select.selectByVisibleText(role);
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.id("confirmpassword"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"));
        element.submit();
    }

}