/*
import wadp.*
import wadp.auth.*
import wadp.service.*
import wadp.domain.*
import java.util.UUID
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver

name = 'mail'

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
    element.sendKeys(mailName + "@gmail.com")
    element = driver.findElementByName("confirmemail")
    element.sendKeys(mailName + "@gmail.com")

    element = driver.findElement(By.id("password"))
    element.sendKeys(password)
    element = driver.findElement(By.id("confirmpassword"))
    element.sendKeys(password)
    element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
    element.submit()
}

def login (String mailName, String password) {
    driver.get("http://localhost:8080/index")
    element = driver.findElementByName("email")
    element.sendKeys(name + "@gmail.com")
    element = driver.findElement(By.id("password"))
    element.sendKeys(password)
    element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
    element.click()
}

description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""

scenario "creation successfully with correct username and password", {
    given 'command new user is selected', {
        registerSetup()
    }
 
    when 'a valid username and password are entered', {
        name = UUID.randomUUID().toString().substring(0, 8)
        createUser(name, "paprika99")
    }

    then 'new user is registered to system', {
        driver.getPageSource().contains("Sinut on rekisteröity palveluun").shouldBe true
    }
}

scenario "can login with successfully generated account", {
    given 'command new user is selected', {
        registerSetup()

        name = UUID.randomUUID().toString().substring(0, 8)
        createUser(name, "paprika99")
    }   
 
    when 'a valid username and password are entered', {
        login(name, "paprika99")
    }

    then  'new credentials allow logging in to system', {
        driver.getPageSource().contains("Minun kurssini").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
        registerSetup()
    }
    when 'a valid username and too short password are entered', {
        createUser("exa", "pap")
    }
    then 'new user is not registered to system', {
        driver.getPageSource().contains("Salasana uudelleen").shouldBe true
    }
}


//Imo tätä ominaisuutta ei tarvita: hojahoja
//scenario "creation fails with correct username and password consisting of letters", {
//    given 'command new user is selected', {
//        registerSetup()
//    }
//    when 'a valid username and password consisting of letters are entered', {
//        element = driver.findElement(By.id("name"))
//        element.sendKeys("esimies")
//        element = driver.findElementByName("email")
//        element.sendKeys("esime@gmail.com")
//        element = driver.findElementByName("confirmemail")
//        element.sendKeys("esime@gmail.com")
//        //select student
//        element = driver.findElement(By.id("password"))
//        element.sendKeys("paprikaaa")
//        element = driver.findElement(By.id("confirmpassword"))
//        element.sendKeys("paprikaaa")
//        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
//        element.submit()
//    }
//    then 'new user is not be registered to system', {
//        driver.getPageSource().contains("Sinut on").shouldBe false
//    }
//}



scenario "creation fails with no email and valid password", {
    given 'command new user is selected', {
        registerSetup()
    }
    when 'a too sort username and valid password are entered', {
        element = driver.findElement(By.id("name"))
        element.sendKeys("esimerr")
        //select student
        element = driver.findElement(By.id("password"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.id("confirmpassword"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
        element.submit()
    }
    then 'new user is not registered to system', {
        driver.getPageSource().contains("Sinut on").shouldBe false
    }
}

scenario "creation fails with already taken username and valid password", {
    given 'command new user is selected', {
        registerSetup()

        name = UUID.randomUUID().toString().substring(0, 8)
        createUser(name, "paprika99")

        driver.get("http://localhost:8080/index")
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"))
        element.click()
    }
    when 'a already taken username and valid password are entered', {
        createUser(name, "paprika99")
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Sinut on").shouldBe false
    }
}

scenario "can not login with account that is not successfully created", {
    given 'command new user is selected', {
        registerSetup()
        createUser("hikipinko", "hikke")
    }
    when 'a invalid username/password are entered', {
        login("hikipinko", "hikke")
    }
    then  'new credentials do not allow logging in to system', {
        driver.getPageSource().contains("Minun kurssini").shouldBe false
    }
}
//*/