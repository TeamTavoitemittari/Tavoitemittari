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
public class GiveGradeTest {

    private HtmlUnitDriver driver;
    private WebElement element;
    private Actions builder;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    public GiveGradeTest() {
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
    public void teacherCanGiveGrade() {
        login("s@a.com", "oppilas");
        joinCourse();
        logout();
        login("t@a.com", "ope");
        element = driver.findElement(By.id("goalometers1"));
        element.click();
        giveGrade("testgrade");
        assertTrue(driver.getPageSource().contains("testgrade"));
        logout();
        login("s@a.com", "oppilas");
        System.out.println(driver.getPageSource());
        assertTrue(driver.getPageSource().contains("testgrade"));
        
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

    private void joinCourse() {
        WebElement mycoursesTab = driver.findElement(By.id("mycoursesTab"));
        WebElement allcourses = driver.findElement(By.id("allcourses"));
        WebElement tavoitemittariin = driver.findElement(By.id("join1"));

        builder.moveToElement(mycoursesTab).moveToElement(allcourses).
                moveToElement(tavoitemittariin).click().build().perform();
    }

    private void giveGrade(String grade) {
        System.out.println(driver.getPageSource());
        driver.findElement(By.id("openGrading" + userService.findUserByEmail("s@a.com").getId())).click();
        element = driver.findElement(By.id("textGrade"));
        element.sendKeys(grade);
        driver.findElement(By.name("asd")).click();

    }

    private void createDummys() {
        userService.createUser("s@a.com", "oppilas", "Ossi Oppilas", "student");
        userService.createUser("t@a.com", "ope", "Olli Oppilas", "teacher");
        courseService.createDummyCourseWithoutUsers(userService.findUserByEmail("t@a.com"));

    }

    private void logout() {
        this.driver.close();
        this.builder = new Actions(driver);
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }

}
