
package integrationtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import wadp.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class ChangePasswordTest {
    
    private String password;
    private String name;
    private HtmlUnitDriver driver;
    private WebElement element;
    
    public ChangePasswordTest() {
    }

    @Before
    public void setUp() {
        name = "test1";
        password = "Testpassword1";
        createUser();
    }

    @After
    public void tearDown() {
        driver.close();
    }
    
    @Test
    public void canChangePasswordAfterLogin() {
        login(name, password);
        getUserdetailsPage();
        passwordChange("Testpassword2", "Testpassword2");
        assertTrue(hasMessage("Salasana vaihdettu."));
    }
    
    @Test
    public void cannotChangePasswordWithoutMatchingPasswords() {
        login(name, "Testpassword2");
        getUserdetailsPage();
        passwordChange("Testipassword2", "Testpassword3");
        assertTrue(hasMessage("Salasanojen pitää olla samoja!"));
    }

    @Test
    public void cannotChangeTooShortPassword() {
        login(name, password);
        getUserdetailsPage();
        passwordChange("aasi", "aasi");
        assertTrue(hasMessage("Salasanan pitää olla ainakin 8 kirjainta!"));
    }

    private void createUser() {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"));
        element.click();

        element = driver.findElement(By.id("name"));
        element.sendKeys("esimerkki");
        element = driver.findElementByName("email");
        element.sendKeys(name + "@gmail.com");
        element = driver.findElementByName("confirmemail");
        element.sendKeys(name + "@gmail.com");
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.id("confirmpassword"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"));
        element.submit();
    }
    
    private void login(String name, String password) {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/");
        element = driver.findElementByName("email");
        element.sendKeys(name + "@gmail.com");
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.click();
    }
    
    private void getUserdetailsPage() {
        element = driver.findElement(By.id("userdetails"));
        element.click();
    }

    private void passwordChange(String password, String confirmPassword) {
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.id("confirmpasswordpassword"));
        element.sendKeys(confirmPassword);
        element = driver.findElement(By.xpath("//button[contains(.,'Vaihda salasana')]"));
        element.submit();
    }

    private boolean hasMessage(String message) {
        return driver.getPageSource().contains(message);
    }

}
