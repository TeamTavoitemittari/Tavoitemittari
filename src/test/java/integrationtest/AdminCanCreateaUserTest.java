package integrationtest;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.Application;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import wadp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class AdminCanCreateaUserTest {

    private HtmlUnitDriver driver;
    private WebElement element;
    
    @Autowired
    private UserService userService;


    public AdminCanCreateaUserTest() {
        driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
    }

      @Before
    public void setUp() {
        createDummyAdmin();
    }
    
    @Test
    public void AdminCanCreateaTeacherAccount() {
        driver.get("http://localhost:8080/index");
        login("admin@a.com", "admin");
        driver.findElement(By.id("newuser")).click();
        createTeacherUser();
        assertTrue(driver.getPageSource().contains("Teacher"));
        driver.close();
        driver = new HtmlUnitDriver();
    }

    @Test
    public void AdminCanCreateaStudentAccount() {
        driver.get("http://localhost:8080/index");
        login("admin@a.com", "admin");
        driver.findElement(By.id("newuser")).click();
        createStudentUser();
        assertTrue(driver.getPageSource().contains("Student"));
        driver.close();
        driver = new HtmlUnitDriver();
    }

    private void createStudentUser() {
        driver.setJavascriptEnabled(true);
        element = driver.findElement(By.id("name"));
        element.sendKeys("Student");
        element = driver.findElementByName("email");
        element.sendKeys("student@a.com");
        element = driver.findElementByName("confirmemail");
        element.sendKeys("student@a.com");
        Select select = new Select(driver.findElement(By.name("userRole")));
        select.selectByVisibleText("student");
        element = driver.findElement(By.id("password"));
        element.sendKeys("paprika99");
        element = driver.findElement(By.id("confirmpassword"));
        element.sendKeys("paprika99");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"));
        element.click();

    }

    private void createTeacherUser() {
        driver.setJavascriptEnabled(true);
        element = driver.findElement(By.id("name"));
        element.sendKeys("Teacher");
        element = driver.findElementByName("email");
        element.sendKeys("teach@a.com");
        element = driver.findElementByName("confirmemail");
        element.sendKeys("teach@a.com");
        Select select = new Select(driver.findElement(By.name("userRole")));
        select.selectByVisibleText("teacher");
        element = driver.findElement(By.id("password"));
        element.sendKeys("paprika99");
        element = driver.findElement(By.id("confirmpassword"));
        element.sendKeys("paprika99");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"));
        element.click();
    }


    private void login(String user, String password) {
        element = driver.findElement(By.id("email"));
        element.sendKeys(user);
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.click();
    }
    
    private void createDummyAdmin(){
        userService.createUser("admin@a.com", "admin", "Arto Admin", "admin");
    }


}