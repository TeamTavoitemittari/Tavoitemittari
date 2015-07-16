
package integrationtest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.Application;
import wadp.repository.UserRepository;
import wadp.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class LoginTest {
    
    private String password;
    private String name;
    private HtmlUnitDriver driver;
    private WebElement element;

    
    public LoginTest() {
    }
    
    @Before
    public void setUp() {

        registerSetup();
        name = "test";
        password = "passworDa1";
        createUser(name, password);
    }
    
    @Test
    public void canLoginWithCorrectPassword() {
        login(name, password);
        assertTrue(hasMessage("Minun kurssini"));
        
    }
    
    @Test
    public void cannotLoginWithWrongPassword() {
        login(name, "Wrongpassword1");
        assertTrue(hasMessage("Tarkista syöttämäsi tiedot!"));
    }
    
    @Test
    public void nonexistentUserCannotLogin() {
        login("Made", password);
        assertTrue(hasMessage("Tarkista syöttämäsi tiedot!"));
    }

    private void registerSetup() {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/index");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"));
        element.click();
    }

    private void createUser(String mailName, String password) {
        element = driver.findElement(By.id("name"));
        element.sendKeys("esimerkki");
        element = driver.findElementByName("email");
        element.sendKeys(mailName + "@gmail.com");
        element = driver.findElementByName("confirmemail");
        element.sendKeys(mailName + "@gmail.com");

        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.id("confirmpassword"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"));
        element.submit();
    }

    private void login(String mailName, String password) {
        driver.get("http://localhost:8080/index");
        element = driver.findElementByName("email");
        element.sendKeys(mailName + "@gmail.com");
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.click();
    }

    private boolean hasMessage(String message) {
        return driver.getPageSource().contains(message);
    }
}
