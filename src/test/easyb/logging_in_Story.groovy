import wadp.*
import wadp.domain.User;
import wadp.service.*
import wadp.auth.*;
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can log in with valid username/password-combination'

before "create a new user", {
    User user = new User()
    
    us.setEmail("example@gmail.com")
    us.setPassword("paprika99")
    us.setName("Esimerkki")
    us.setUserRole("oppilas");
    userRepository.save(user)
}

scenario "user can login with correct password", {
        given 'right view is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/");
        driver.getPageSource()
    }

    when 'a valid username and password are entered', {
        element = driver.findElementByName("email");
        element.sendKeys("example@gmail.com");
        element = driver.findElement(By.id("password"));
        element.sendKeys("paprika99");
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.submit();
    }

    then 'user will be logged in to system', {
        driver.getPageSource().contains("EI OLE TUNNUSTA? EI HÄTÄÄ.").shouldBe false
        driver.getPageSource().contains("Minun kurssini").shouldBe true
    } 
   
}

/*
scenario "user can not login with incorrect password", {
    given 'right view is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/");
    }
    when 'a valid username and incorrect password are entered', {
        element = driver.findElementByName("email");
        element.sendKeys("example@gmail.com");
        element = driver.findElement(By.id("password"));
        element.sendKeys("papri");
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.submit();
    }
    then 'user will not be logged in to system', {
        driver.getPageSource()
        driver.getPageSource().contains("EI OLE TUNNUSTA? EI HÄTÄÄ.").shouldBe true
        driver.getPageSource().contains("Minun kurssini").shouldBe false
    }
}

scenario "nonexistent user can not login to ", {
   /* given 'right view is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/");
    }
    when 'a nonexistent username and some password are entered', {
        element = driver.findElementByName("email");
        element.sendKeys("e@gmail.com");
        element = driver.findElement(By.id("password"));
        element.sendKeys("paprika99");
        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));
        element.submit();
    }
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("EI OLE TUNNUSTA? EI HÄTÄÄ.").shouldBe true
        driver.getPageSource().contains("Minun kurssini").shouldBe false
    }  
}
*/


def createUser() {
    //def user = new User()
    us.setEmail("example@gmail.com")
    us.setPassword("paprika99")
    us.setName("Esimerkki")
    us.setUserRole("oppilas");
    userRepository.save(user)
}