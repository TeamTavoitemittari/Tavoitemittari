package integrationtest;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.interactions.Actions;
import wadp.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.service.CourseService;
import wadp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class ViewPersonalCourseProgressTest {

    private Actions builder;
    private WebElement element;
    private HtmlUnitDriver driver;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    public ViewPersonalCourseProgressTest() {
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }

    @Before
    public void setUp() {
        createDummys();
    }

    @Test
    public void studentCanViewPersonalCourseProgress() {
        login("s@a.com", "oppilas");
        joinCourse();
        getCourseProgressPage();
        assertTrue(driver.getPageSource().contains("9-10"));
    }

    @Test
    public void studentCanChangePersonalCourseProgress() {
        login("s@a.com", "oppilas");
        joinCourse();
        getCourseProgressPage();
        
        WebElement checkProgress = driver.findElement(By.linkText("Mustat aukot"));
        checkProgress.click();
        assertTrue(driver.getPageSource().contains("Osaan"));
        element = driver.findElement(By.xpath("//button[contains(.,'Osaan')]"));
        
        element.submit();
        assertTrue(driver.findElement(By.xpath("//button[contains(.,'Osaan')]")).getCssValue("background-color").equals("rgb(132, 219, 183)"));

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

    private void getCourseProgressPage() {
        driver.setJavascriptEnabled(true);
        WebElement mycoursesTab = driver.findElement(By.id("mycoursesTab"));
        WebElement owncourses = driver.findElement(By.name("owncourses"));
        WebElement tavoitemittariin = driver.findElement(By.id("tavoitemittari1"));
        builder.moveToElement(mycoursesTab).moveToElement(owncourses).
                moveToElement(tavoitemittariin).click().build().perform();
    }

    private void createDummys() {
        userService.createUser("s@a.com", "oppilas", "Ossi Oppilas", "student");
        userService.createUser("t@a.com", "ope", "Olli Oppilas", "teacher");
        courseService.createDummyCourseWithoutUsers(userService.findUserByEmail("t@a.com"),true  );

    }

    private void joinCourse() {
        driver.setJavascriptEnabled(true);
        WebElement mycoursesTab = driver.findElement(By.id("mycoursesTab"));
        WebElement owncourses = driver.findElement(By.name("owncourses"));
        WebElement allcourses = driver.findElement(By.name("allcourses"));
        WebElement join = driver.findElement(By.id("join1"));
        builder.moveToElement(mycoursesTab).moveToElement(owncourses).
                moveToElement(allcourses).moveToElement(join).click().build().perform();

    }
}
