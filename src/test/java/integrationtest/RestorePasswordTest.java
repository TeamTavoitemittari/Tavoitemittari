package integrationtest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.Application;
import wadp.service.CourseService;
import wadp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@IntegrationTest({"server.port=8080"})
public class RestorePasswordTest {

    private HtmlUnitDriver driver;
    private WebElement element;
    private Actions builder;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public RestorePasswordTest() {
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }

    @Before
    public void setUp() {
        createDummys();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void restorePassword() {
        triggerRestorePw("s@a.com");
        login("a@a.com", "admin");
        getResetPWPage();
        resetPW("newPW");
        logout();
        login("s@a.com", "oppilas");
        assertTrue(driver.getPageSource().contains("Tarkista syöttämäsi tiedot!"));
        login("s@a.com", "newPW");
        assertTrue(driver.getPageSource().contains("Minun kurssini"));
    }

    private void login(String email, String password) {
        driver.get("http://localhost:8080/index");
        element = driver.findElement(By.id("email"));
        element.sendKeys(email);
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.click();
    }

    private void triggerRestorePw(String email) {
        driver.get("http://localhost:8080/index");
        driver.findElement(By.id("restorepw")).click();
        element = driver.findElement(By.id("email"));
        element.sendKeys(email);
        element = driver.findElement(By.xpath("//button[contains(.,'Lähetä ilmoitus')]"));
        element.click();
    }

    private void getResetPWPage() {
        driver.findElement(By.id("passwords")).click();
        driver.findElement(By.id("resetPW" + userService.findUserByEmail("s@a.com").getId())).click();
    }
    
    private void resetPW(String pw){
        driver.findElement(By.id("password")).sendKeys(pw);
        driver.findElement(By.xpath("//button[contains(.,'Vaihda salasana')]")).click();
    }

    private void createDummys() {
        userService.createUser("s@a.com", "oppilas", "Ossi Oppilas", "student");
        userService.createUser("t@a.com", "ope", "Olli Oppilas", "teacher");
        userService.createUser("a@a.com", "admin", "Arto Admin", "admin");
        courseService.createDummyCourseWithoutUsers(userService.findUserByEmail("t@a.com"));

    }

    private void logout() {
        this.driver.close();
        this.builder = new Actions(driver);
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }

}
