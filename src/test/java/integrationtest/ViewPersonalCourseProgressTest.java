
package integrationtest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import org.openqa.selenium.interactions.Actions;
import wadp.*;
import wadp.domain.*;
import wadp.service.*;
import wadp.auth.*;
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
public class ViewPersonalCourseProgressTest {

    private Actions builder;
    private WebElement element;
    private HtmlUnitDriver driver;
    
    public ViewPersonalCourseProgressTest() {
        this.driver = new HtmlUnitDriver();
        this.builder = new Actions(driver);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void studentCanViewPersonalCourseProgress() {
        login("oppilas@a.com", "oppilas");
        getCourseProgressPage();
        assertTrue(driver.getPageSource().contains("9-10"));
    }

    @Test
    public void studentCanChangePersonalCourseProgress() {
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
        WebElement courseCommentTab = driver.findElement(By.id("courseCommentTab"));
        WebElement owncourses = driver.findElement(By.name("updatetab"));
        WebElement tavoitemittariin = driver.findElement(By.id("tavoitemittari1"));
        builder.moveToElement(courseCommentTab).moveToElement(owncourses).
                moveToElement(tavoitemittariin).click().build().perform();
    }
}
