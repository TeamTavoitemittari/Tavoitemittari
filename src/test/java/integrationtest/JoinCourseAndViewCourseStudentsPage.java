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
//    @Test
//    public void teacherCanViewCourseStudentPage() {
//        teacherLogin();
//        joinCourse();
//        assertTrue(driver.getPageSource().contains("Sinut on liitetty kurssille!"));
//        getCourseProgressPage();
//        getCourseStudentPage();
//        assertTrue(driver.getPageSource().contains("Olli Opettaja"));
//    }
    

    @Test
    public void studentCantSeeCourseStudentPage() {
        
        studentLogin();
        getCourseProgressPage();
        assertTrue(driver.getPageSource().contains("studenttab")==false);
        assertTrue(driver.getPageSource().contains("commenttab")==true);
    }

    @After
    public void tearDown() {

    }

    private void studentLogin() {
        login("oppilas@a.com", "oppilas");
    }

    private void teacherLogin() {
        login("ope@a.com", "ope");
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
        driver.findElement(By.id("join1")).click();
    }

    private void getCourseProgressPage() {
        driver.setJavascriptEnabled(true);
        WebElement courseCommentTab = driver.findElement(By.id("courseCommentTab"));
        WebElement owncourses = driver.findElement(By.name("updatetab"));
        WebElement tavoitemittariin = driver.findElement(By.id("tavoitemittari1"));

        builder.moveToElement(courseCommentTab).moveToElement(owncourses).
                moveToElement(tavoitemittariin).click().build().perform();

    }

    private void getCourseStudentPage() {
        WebElement coursecomment = driver.findElement(By.id("courseCommentTab"));
        WebElement commenttab = driver.findElement(By.name("studenttab"));
        builder.moveToElement(coursecomment).moveToElement(commenttab).click().build().perform();
    }
    
     private void createDummys() {
        userService.createUser("s@a.com", "oppilas", "Ossi Oppilas", "student");
        userService.createUser("t@a.com", "ope", "Olli Oppilas", "teacher");
       courseService.createDummyCourseWithoutUsers(userService.findUserByEmail("t@a.com"));

    }
    

}
