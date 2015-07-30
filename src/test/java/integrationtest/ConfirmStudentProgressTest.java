package integrationtest;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.Application;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class ConfirmStudentProgressTest {


    private Actions builder;
    private WebElement element;
    private HtmlUnitDriver driver;
    private String teacherEmail;
    private String teacherPassword;



    public ConfirmStudentProgressTest() {
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }

    @Before
    public void setUp(){
        teacherEmail = "ope@a.com";
        teacherPassword = "ope";
    }

    @Test
    public void teacherCanConfirmStudentReportedProgress() {
        System.out.println(driver.getPageSource());
        login(teacherEmail, teacherPassword);
        getCourseProgressPageAsTeacher();
        assertTrue(driver.getPageSource().contains("9-10"));
        WebElement checkProgress = driver.findElement(By.linkText("Astrofysiikka"));
        checkProgress.click();
        element = driver.findElement(By.xpath("//button[contains(.,'Vahvista')]"));
        element.submit();
        assertTrue(driver.getPageSource().contains("Osaa"));
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

    private void getCourseProgressPageAsTeacher() {
        element = driver.findElement(By.id("hallinnointi-toggle-menu"));
        element.click();
        element = driver.findElement(By.id("hdd-b"));
        element.click();
        element = driver.findElement(By.partialLinkText("Matti"));
        element.click();
        element = driver.findElement(By.partialLinkText("Tähtitiede 1"));
        element.click();
    }

}
