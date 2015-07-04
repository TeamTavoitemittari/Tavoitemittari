/*
package integrationtest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.Application;
import wadp.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class AddCommentTest {
    
    private HtmlUnitDriver driver;
    private WebElement element;
    
    public AddCommentTest() {}

    
    @Before
    public void setUp() {
        
    }
    
    @Test
    public void studentCanAddComment() {
        studentLogin();
        addComment("Oli helppo");
        System.out.println(driver.getPageSource());
        assertTrue(driver.getPageSource().contains("Oli helppo"));
    }
    
    @After
    public void tearDown() {
        
    }
    
    private void studentLogin() {
        login("oppilas@a.com", "oppilas");
        getCommentpage();
    }
    
    private void teacherLogin() {
        createUser("ope1", "Opettaja21", "teacher");
        login("ope1@gmail.com", "Opettaja21");
        getCommentpage();
    }
    
   
    
    private void createUser(String mailName, String password, String role) {
        driver = new HtmlUnitDriver(true);
        
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
    
    private void login(String email, String password) {  
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/index");
        element = driver.findElementByName("email");
        element.sendKeys(email);
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.click();
    }
    
    private void getCommentpage() {
        element = driver.findElement(By.name("tavoitemittari"));
        element.click();
        
        element = driver.findElement(By.name("commenttab"));
        element.click();

    }

    private void addComment(String comment) {
        driver.setJavascriptEnabled(true);
        element = driver.findElement(By.id("edit1"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        //element.click();
        System.out.println(driver.getPageSource());
        //TODO Katso miten sendaa textarea
        element = driver.findElement(By.id("comment1"));
        element.sendKeys(comment);
        
        element = driver.findElement(By.xpath("//button[contains(.,'Tallenna')]"));
        executor.executeScript("arguments[0].click();", element);
        //element.click();
        System.out.println(driver.getPageSource());
    }
    
}
*/
