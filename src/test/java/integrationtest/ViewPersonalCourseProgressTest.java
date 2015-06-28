
package integrationtest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wadp.*;
import wadp.domain.*;
import wadp.service.*;
import wadp.auth.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ViewPersonalCourseProgressTest {
    
    public ViewPersonalCourseProgressTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

//At the moment this is a redundant test.
/*
description 'Anyone can view the course goal view for a course'

    @Test
    public void User can view the course demo for a course", {
        createUser("oppilas", "Oppilas21", "student");
        login("oppilas@gmail.com", "Oppilas21");
        //todo
        assertTrue(driver.getPageSource().contains("9-10"));
    } 
    
    private void createUser(String mailName, String password, String role) {
        driver.get("http://localhost:8080/index");
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"));
        element.click();
        element = driver.findElement(By.id("name"));
        element.sendKeys(mailName);
        element = driver.findElementByName("email");
        element.sendKeys(mailName + "@gmail.com");
        element = driver.findElementByName("confirmemail");
        element.sendKeys(mailName + "@gmail.com");
        Select select = new Select(driver.findElement(By.name("userRole")));
        select.selectByVisibleText(role);
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.id("confirmpassword"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"));
        element.submit();
    }
    
    private void login(String email, String password) {
        driver.get("http://localhost:8080/index");
        element = driver.findElementByName("email");
        element.sendKeys(email);
        element = driver.findElement(By.id("password"));
        element.sendKeys(password);
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.click();
    }

*/
}
