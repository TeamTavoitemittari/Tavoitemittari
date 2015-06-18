/*
import wadp.*
import wadp.auth.*
import wadp.service.*
import wadp.domain.*
import java.util.UUID
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.support.ui.Select

description "teacher can create new courses"

def login(String email, String password) {
    driver = new HtmlUnitDriver()
    driver.get("http://localhost:8080/index")
    element = driver.findElementByName("email")
    element.sendKeys(email)
    element = driver.findElement(By.id("password"))
    element.sendKeys(password)
    element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"))
    element.click()
}

def getCourseCreationPage() {
    element = driver.findElement(By.id("hallinnointi-toggle-menu"))
    element.click()
    element = driver.findElement(By.id("hdd-a"))
    element.click()
    driver.getPageSource()
}

def createCourse(String name, String description) {
    element = driver.findElement(By.id("name"))
    element.sendKeys(name)
    element = driver.findElement(By.id("description"))
    element.sendKeys(description)
    element = driver.findElement(By.xpath("//button[contains(.,'Lisää')]"))
    element.submit()
}

scenario "teacher can create a new courses successfully", {
    given 'user with teacher credentials goes to course creation page', {
        login("ope@a.com", "ope")
        getCourseCreationPage()
    }

    when 'teacher creates a new course', {
        name = UUID.randomUUID().toString().substring(0, 8)
        createCourse(name, "Tällä kurssila käsitellään jotain")
    }

    then 'new course can be found on course listing page', {
        driver.getPageSource().contains(name).shouldBe true
    }
}

scenario "student can't create a new course", {
    given 'user with student credentials logins', {
        login("oppilas@.com", "oppilas")    
    }

    when 'student tries to acess course creation menu', {        
        try {
            getCourseCreationPage()  
            createCourse("Jokulogia", "Tällä kursilla käsitellään vielä enemmän jotain")
        }
        catch(Exception e) {            
        }
        
    }

    then 'nothing gets added to the course page', {
        driver.getPageSource().contains(name).shouldBe false
    }
}

scenario "student can't bypass role chuck by going straight to course creation", {
    given 'description', {
        login("oppilas@.com", "oppilas") 
    }

    when 'description', {

        try {
            driver.get("http://localhost:8080/course")
            createCourse("Jokulogia", "Jotain")
        }
        catch(Exception e) {

        }
        
    }

    then 'description', {
        driver.getPageSource().contains(name).shouldBe false
    }
}
//*/