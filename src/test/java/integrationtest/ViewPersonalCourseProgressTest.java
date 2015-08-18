
package integrationtest;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.interactions.Actions;
import wadp.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import wadp.service.CourseService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class ViewPersonalCourseProgressTest {

    private Actions builder;
    private WebElement element;
    private HtmlUnitDriver driver;
        @Autowired
    private CourseService courseService;
    
    
    public ViewPersonalCourseProgressTest() {
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }
    
    @Test
    public void studentCanViewPersonalCourseProgress() {
        courseService.createDummyCourse();
        login("oppilas@a.com", "oppilas");
        getCourseProgressPage();
        assertTrue(driver.getPageSource().contains("9-10"));
    }

    @Test
    public void studentCanChangePersonalCourseProgress() {
        courseService.createDummyCourse();
        login("oppilas@a.com", "oppilas");
        getCourseProgressPage();
        //System.out.println(driver.getPageSource());
        WebElement checkProgress = driver.findElement(By.linkText("Mustat aukot"));
        checkProgress.click();
        assertTrue(driver.getPageSource().contains("Osaan"));
        element = driver.findElement(By.xpath("//button[contains(.,'Osaan')]"));
        //assertFalse(element.getAttribute("id").equals("done"); osaan buttoneiden specifiointi!?
        element.submit();

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
}
