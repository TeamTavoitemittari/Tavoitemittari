
package integrationtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import wadp.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class CreateaNewAccountTest{

    private String name;
    private HtmlUnitDriver driver;
    private WebElement element;

    @Before
    public void setUp() {

        name = "test2";
    }
    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void studentCanCreateUserWithValidUsernameAndPassword() {
        registerSetup();
        createUser(name, "passWord1");
        assertTrue(hasMessage("Sinut on rekisteröity palveluun sähköpostiosoitteella "));
    }
    
    @Test
    public void studentCanCreateUserWithValidUsernameAndPasswordAndLogin() {
        registerSetup();
        createUser("test3", "passWord1");
        assertTrue(hasMessage("Sinut on rekisteröity palveluun sähköpostiosoitteella "));
        login("test3", "passWord1");
        assertTrue(hasMessage("Minun kurssini"));
        assertFalse(hasMessage("Hallinnointi"));
    }
    
    @Test
    public void studentCannotCreateUserWithoutValidPassword() {
        registerSetup();
        createUser("test4", "pass");
        assertTrue(hasMessage("Salasanan pitää olla ainakin 8 kirjainta!"));
        login("test4", "pass");
        assertTrue(hasMessage("Tarkista syöttämäsi tiedot!"));
    }
    
    @Test
    public void studentCannotCreateUserWithoutValidEmail() {
        registerSetup();
        
        element = driver.findElement(By.id("name"));
        element.sendKeys("esimerr");
        //select student
        element = driver.findElement(By.id("password"));
        element.sendKeys("paprika99");
        element = driver.findElement(By.id("confirmpassword"));
        element.sendKeys("paprika99");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"));
        element.submit();

        assertTrue(hasMessage("Sähköpostia ei voi jättää tyhjäksi!"));
    }
    
    @Test
    public void studentCannotCreateUserWithAlreadyTakenEmail() {
        registerSetup();
        createUser(name, "TestPassword0");

        registerSetup();
        createUser(name, "Wrongpass0");
        assertTrue(hasMessage("Sähköpostiosoite on jo rekisteröity palveluun!"));
    }
     
    
    private void registerSetup() {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/index");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"));
        element.click();
    }

    private void createUser(String mailName, String password) {
        driver.get("http://localhost:8080/index");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"));
        element.click();
        element = driver.findElement(By.id("name"));
        element.sendKeys(mailName);
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
