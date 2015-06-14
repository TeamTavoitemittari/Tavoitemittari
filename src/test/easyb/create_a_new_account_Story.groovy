/*
import wadp.*
import wadp.auth.*
import wadp.service.*
import wadp.domain.*
import java.util.UUID
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver

nimi = 'mail'

def registerSetup() {
    driver = new HtmlUnitDriver()
    driver.get("http://localhost:8080/index")
    element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"))
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
        nimi = UUID.randomUUID().toString().substring(0, 8)
        element = driver.findElement(By.id("name"))
        element.sendKeys("esimerkki")
        element = driver.findElementByName("email")
        element.sendKeys(nimi + "@gmail.com")
        element = driver.findElementByName("confirmemail")
        element.sendKeys(nimi + "@gmail.com")
        
        element = driver.findElement(By.id("password"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.id("confirmpassword"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
        element.submit()
    }

    then 'new user is registered to system', {
        driver.getPageSource().contains("Sinut on rekisteröity palveluun").shouldBe true
    }
}

scenario "can login with successfully generated account", {
    given 'command new user is selected', {
        registerSetup()
        nimi = UUID.randomUUID().toString().substring(0, 8)
        element = driver.findElement(By.id("name"))
        element.sendKeys("esimerkki")
        element = driver.findElementByName("email")
        element.sendKeys(nimi + "@test.com")
        element = driver.findElementByName("confirmemail")
        element.sendKeys(nimi + "@test.com")
        
        element = driver.findElement(By.id("password"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.id("confirmpassword"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
        element.submit()
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
        element.click()
    }   
 
    when 'a valid username and password are entered', {
        element = driver.findElementByName("email")
        element.sendKeys(nimi + "@test.com")
        element = driver.findElement(By.id("password"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
        element.click()
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
        element = driver.findElement(By.id("name"))
        element.sendKeys("esmes")
        element = driver.findElementByName("email")
        element.sendKeys("exa@gmail.com")
        element = driver.findElementByName("confirmemail")
        element.sendKeys("exa@gmail.com")
        //select student
        element = driver.findElement(By.id("password"))
        element.sendKeys("pap")
        element = driver.findElement(By.id("confirmpassword"))
        element.sendKeys("pap")
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
        element.submit()
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
        nimi = UUID.randomUUID().toString().substring(0, 8)
        element = driver.findElement(By.id("name"))
        element.sendKeys("esimerkki")
        element = driver.findElementByName("email")
        element.sendKeys(nimi + "@gmail.com")
        element = driver.findElementByName("confirmemail")
        element.sendKeys(nimi + "@gmail.com")
        
        element = driver.findElement(By.id("password"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.id("confirmpassword"))
        element.sendKeys("paprika99")
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy')]"))
        element.submit()
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
        element.click()
        element = driver.findElement(By.xpath("//button[contains(.,'Rekisteröidy!')]"))
        element.click()
    }
    when 'a already taken username and valid password are entered', {
        element = driver.findElement(By.id("name"))
        element.sendKeys("esim")
        element = driver.findElementByName("email")
        element.sendKeys(nimi + "@gmail.com")
        element = driver.findElementByName("confirmemail")
        element.sendKeys(nimi + "@gmail.com")
        
        element = driver.findElement(By.id("password"))
        element.sendKeys("ananas00")
        element = driver.findElement(By.id("confirmpassword"))
        element.sendKeys("ananas00")
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Sinut on").shouldBe false
    }
}

scenario "can not login with account that is not successfully created", {
    given 'command new user is selected', {
        registerSetup()
    }
    when 'a invalid username/password are entered', {
        element = driver.findElementByName("email")
        element.sendKeys("esimerkki@gmail.com")
        element = driver.findElement(By.id("password"))
        element.sendKeys("ananas00")
    }
    then  'new credentials do not allow logging in to system', {
        driver.getPageSource().contains("Minun kurssini").shouldBe false
    }
}
//*/