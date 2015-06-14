
import wadp.*
import wadp.auth.*;
import wadp.service.*;
import wadp.domain.*;
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

/*
description """User can change his password if it's proper"""

before "loggin", {
    driver = new HtmlUnitDriver()
    driver.get("http://localhost:8080/index")
    element = driver.findElementByName("email")
    element.sendKeys("oppilas@a.com")
    element = driver.findElement(By.id("password"))
    element.sendKeys("oppilas")
    element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
    element.click()
}

scenario "changing successfully with valid password", {
    given 'command user details is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080/index")
        element = driver.findElementByName("email")
        element.sendKeys("oppilas@a.com")
        element = driver.findElement(By.id("password"))
        element.sendKeys("oppilas")
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
        element.click()

        element = driver.findElement(By.id("dropdown-toggle-menu"))
        element.click()
        element = driver.findElement(By.id("dd-a"))
        element.click()
        driver.getPageSource()
    }

    when 'a new password is entered', {
        element = driver.findElement(By.id("password"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.id("confirmpasswordpassword"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.xpath("//button[contains(.,'Vaihda salasana')]"))
        element.submit()
    }

    then 'new password is registered to system', {
        driver.getPageSource().contains("Takaisin omiin tietoihin").shouldBe true
    }
}
//*/
