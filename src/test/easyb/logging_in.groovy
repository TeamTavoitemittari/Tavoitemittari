/*
import wadp.*
import wadp.auth.*
import java.util.UUID
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver

description 'User can log in with valid username/password-combination'

nimi = 'mail'

def registerSetup() {
    driver = new HtmlUnitDriver()
    driver.get("http://localhost:8080/index")
    element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"))
    element.click()
}

def createUser(String mailName, String password) {    
    element = driver.findElement(By.id("name"))
    element.sendKeys("esimerkki")
    element = driver.findElementByName("email")
    element.sendKeys(mailName + "@test.com")
    element = driver.findElementByName("confirmemail")
    element.sendKeys(mailName + "@test.com")

    element = driver.findElement(By.id("password"))
    element.sendKeys(password)
    element = driver.findElement(By.id("confirmpassword"))
    element.sendKeys(password)
    element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
    element.submit()
    element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
    element.click()
}

def login(String mailName, String password) {
    element = driver.findElementByName("email")
    element.sendKeys(mailName + "@test.com")
    element = driver.findElement(By.id("password"))
    element.sendKeys(password)
    element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
    element.click()
}

scenario "user can login with correct password", {
    given 'right view is selected', {
        nimi = UUID.randomUUID().toString().substring(0, 8)
        registerSetup()
        createUser(nimi, "paprika99")
    }

    when 'a valid username and password are entered', {
        login(nimi, "paprika99")
    }

    then 'user will be logged in to system', {
        driver.getPageSource().contains("Minun kurssini").shouldBe true
    } 
}


scenario "user can not login with incorrect password", {
    given 'right view is selected', {
        nimi = UUID.randomUUID().toString().substring(0, 8)
        registerSetup()
        createUser(nimi, "paprika99")
    }
    when 'a valid username and incorrect password are entered', {
        login(nimi, "papri")
    }
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("Minun kurssini").shouldBe false
    }
}

scenario "nonexistent user can not log in", {
    given 'right view is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080/index")
    }
    when 'a nonexistent username and some password are entered', {
        login("e", "paprika99")
    }
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("Minun kurssini").shouldBe false
    }  
}
//*/