import wadp.*
import wadp.domain.*
import wadp.service.*
import wadp.auth.*;
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can log in with valid username/password-combination'

before "create a new user", {
    User us = new User();
    us.setEmail("example@gmail.com")
    us.setPassword("paprika99")
    us.setName("Esimerkki")
    us.setUserRole("oppilas");
}

scenario "user can login with correct password", {
    given 'command login selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8080/index");
        element = driver.findElement(By.name("email"));
        element.sendKeys("example@gmail.com");
        element = driver.findElement(By.name("password"));
        element.sendKeys("paprika99");
    }

    when 'a valid username and password are entered', {

        element = driver.findElement(By.linkText("Kirjaudu sisään"));
        element.submit();
    }

    then 'user will be logged in to system', {
        driver.getPageSource().contains("Minun kurssini").shouldBe true
        driver.getPageSource().contains("Kaikki kurssit").shouldBe true
        driver.getPageSource().contains("Ilmoittaudu").shouldBe true
    }
}

scenario "user can not login with incorrect password", {
    given 'command login selected', {}
    when 'a valid username and incorrect password are entered', {}
    then 'user will not be logged in to system', {}
}

scenario "nonexistent user can not login to ", {
    given 'command login selected', {}
    when 'a nonexistent username and some password are entered', {}
    then 'user will not be logged in to system', {}
}