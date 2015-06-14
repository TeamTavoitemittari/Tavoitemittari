/*
import wadp.*
import wadp.domain.*
import wadp.service.*
import wadp.auth.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver

description 'Anyone can view the course goal view for a course'

def login() {
    driver = new HtmlUnitDriver()
    driver.get("http://localhost:8080/index")
    element = driver.findElementByName("email")
    element.sendKeys("oppilas@a.com")
    element = driver.findElement(By.id("password"))
    element.sendKeys("oppilas")
    element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
    element.click()
}

scenario "User can view the course demo for a course", {
    given 'correct view is selected', {
        login()
    }

    when 'user looks at the page', {
    }

    then 'user will see the course goal view', {
        driver.getPageSource().contains("9-10").shouldBe true
    } 

}
//*/