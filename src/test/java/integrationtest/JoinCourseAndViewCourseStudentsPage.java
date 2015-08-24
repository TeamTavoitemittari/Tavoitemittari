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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.Application;
import wadp.service.CourseService;
import wadp.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class JoinCourseAndViewCourseStudentsPage {

    private HtmlUnitDriver driver;
    private WebElement element;
    private Actions builder;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    public JoinCourseAndViewCourseStudentsPage() {
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }

    @Before
    public void setUp() {
        createDummys();
    }

    //Test structure became irrelevant due to changes in the teacher's mycourses view
    @Test
    public void teacherCanViewCourseStudentPage() {
        studentLogin();
        joinCourse();
        logout();
        teacherLogin();
        getCourseProgressPageAsTeacher();
        getCourseStudentPage();
        System.out.println(driver.getCurrentUrl());
        assertTrue(driver.getPageSource().contains("Tähtikuviot"));
    }

    @Test
    public void studentCantSeeCourseStudentPage() {

        studentLogin();
        joinCourse();
        getCourseProgressPage();

        assertTrue(driver.getPageSource().contains("studenttab") == false);
        assertTrue(driver.getPageSource().contains("commenttab") == true);
    }

    @After
    public void tearDown() {

    }

    private void studentLogin() {
        login("s@a.com", "oppilas");
    }

    private void teacherLogin() {
        login("t@a.com", "ope");
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
        driver.setJavascriptEnabled(true);
        WebElement mycoursesTab = driver.findElement(By.id("mycoursesTab"));
        WebElement owncourses = driver.findElement(By.name("owncourses"));
        WebElement allcourses = driver.findElement(By.name("allcourses"));
        WebElement join = driver.findElement(By.id("join1"));
        builder.moveToElement(mycoursesTab).moveToElement(owncourses).
                moveToElement(allcourses).moveToElement(join).click().build().perform();

    }

    private void getCourseProgressPage() {
        driver.setJavascriptEnabled(true);
        WebElement mycoursesTab = driver.findElement(By.id("mycoursesTab"));
        WebElement owncourses = driver.findElement(By.name("owncourses"));
        WebElement tavoitemittariin = driver.findElement(By.id("tavoitemittari1"));

        builder.moveToElement(mycoursesTab).moveToElement(owncourses).
                moveToElement(tavoitemittariin).click().build().perform();

    }

    private void getCourseProgressPageAsTeacher() {
        driver.setJavascriptEnabled(true);
        driver.findElement(By.id("goalometers1")).click();
    }

    private void getCourseStudentPage() {
        driver.findElementById("goalometer" + userService.findUserByEmail("s@a.com").getId()).click();
    }

    private void createDummys() {
        userService.createUser("s@a.com", "oppilas", "Ossi Oppilas", "student");
        userService.createUser("t@a.com", "ope", "Olli Oppilas", "teacher");
        courseService.createDummyCourseWithoutUsers(userService.findUserByEmail("t@a.com"));

    }
     private void logout(){
        this.driver.close();
        this.builder = new Actions(driver);
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }

}
