/*
import wadp.*
import wadp.auth.*
import wadp.service.*
import wadp.domain.*
import java.util.UUID
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.support.ui.Select

name = UUID.randomUUID().toString().substring(0, 8)
password = 'testPassword1'

def createUser() {
    driver = new HtmlUnitDriver()
    driver.get("http://localhost:8080/index")
    element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"))
    element.click()

    element = driver.findElement(By.id("name"))
    element.sendKeys("esimerkki")
    element = driver.findElementByName("email")
    element.sendKeys(name + "@gmail.com")
    element = driver.findElementByName("confirmemail")
    element.sendKeys(name + "@gmail.com")
    element = driver.findElement(By.id("password"))
    element.sendKeys(password)
    element = driver.findElement(By.id("confirmpassword"))
    element.sendKeys(password)
    element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
    element.submit()
}

def login() {
    driver = new HtmlUnitDriver()
    driver.get("http://localhost:8080/index")
    element = driver.findElementByName("email")
    element.sendKeys(name + "@gmail.com")
    element = driver.findElement(By.id("password"))
    element.sendKeys(password)
    element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
    element.click()
}

def getUserdetailsPage() {
    element = driver.findElement(By.id("dropdown-toggle-menu"))
    element.click()
    element = driver.findElement(By.id("dd-a"))
    element.click()
    driver.getPageSource()
}

def passwordChange(String password, String confirmPassword) {
    element = driver.findElement(By.id("password"))
    element.sendKeys(password)
    element = driver.findElement(By.id("confirmpasswordpassword"))
    element.sendKeys(confirmPassword)
    element = driver.findElement(By.xpath("//button[contains(.,'Vaihda salasana')]"))
    element.submit()
}

def hasMessage(String message) {
    return driver.getPageSource().contains(message)
}

description """User can change his password if it's proper"""

before "setup user for tests", {
    createUser()
}

scenario "changing successfully with valid password", {
    given 'command user details is selected', {
        login()
        getUserdetailsPage()
    }

    when 'a new password is entered', {
        passwordChange("paprika99", "paprika99")
    }

    then 'new password is registered to system', {
        hasMessage("Takaisin omiin tietoihin").shouldBe true
        password = 'paprika99'
    }
}

scenario "cannot change password if the fields don't match", {
    given 'get to user details page', {
        login()
        getUserdetailsPage()
    }
    
    when 'two unmatching passwords are entered', {
        passwordChange("paprika99", "paprika98")
    }
    
    then 'confimation page does not show up', {
        hasMessage("Takaisin omiin tietoihin").shouldBe false
    }
}

scenario "cannot change password if the password is too short", {
    given 'description', {
        login()
        getUserdetailsPage()
    }

    when 'description', {
        passwordChange("aasi", "aasi")
    }

    then 'description', {
        hasMessage("Takaisin omiin tietoihin").shouldBe false
    }
}
//*/
