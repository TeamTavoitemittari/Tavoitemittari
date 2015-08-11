package integrationtest;import org.junit.After;import org.junit.AfterClass;import org.junit.Before;import org.junit.BeforeClass;import org.junit.Test;import static org.junit.Assert.*;import static org.junit.Assert.*;import org.junit.runner.RunWith;import org.openqa.selenium.*;import org.openqa.selenium.htmlunit.HtmlUnitDriver;import org.openqa.selenium.interactions.Action;import org.openqa.selenium.interactions.Actions;import org.openqa.selenium.support.ui.Select;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.IntegrationTest;import org.springframework.boot.test.SpringApplicationConfiguration;import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;import org.springframework.test.context.web.WebAppConfiguration;import wadp.Application;import wadp.service.UserService;@RunWith(SpringJUnit4ClassRunner.class)@SpringApplicationConfiguration(classes = Application.class)@WebAppConfiguration@IntegrationTest({"server.port=8080"})public class AddCommentTest {        private HtmlUnitDriver driver;    private WebElement element;    private Actions builder;        public AddCommentTest() {        this.driver = new HtmlUnitDriver();        this.builder = new Actions(driver);    }        @Before    public void setUp() {            }        @Test    public void studentCanAddComment() {        studentLogin();        addComment("Oli aluksi hankala - Matti");        assertTrue(driver.getPageSource().contains("Oli aluksi hankala - Matti"));    }        @After    public void tearDown() {            }        private void studentLogin() {        login("oppilas@a.com", "oppilas");        getCommentpage();    }        private void teacherLogin() {        login("ope@a.com", "ope");        getCommentpage();    }    private void login(String email, String password) {        driver.get("http://localhost:8080/index");        element = driver.findElement(By.id("email"));        element.sendKeys(email);        element = driver.findElement(By.id("password"));        element.sendKeys(password);        element = driver.findElement(By.xpath("//button[contains(.,'Kirjaudu sisään')]"));        element.click();    }        private void getCommentpage() {        driver.setJavascriptEnabled(true);        WebElement courseCommentTab = driver.findElement(By.id("courseCommentTab"));        WebElement owncourses = driver.findElement(By.name("updatetab"));        WebElement tavoitemittariin = driver.findElement(By.id("tavoitemittari1"));        builder.moveToElement(courseCommentTab).moveToElement(owncourses).                moveToElement(tavoitemittariin).click().build().perform();        WebElement coursecomment = driver.findElement(By.id("courseCommentTab"));        WebElement commenttab = driver.findElement(By.name("commenttab"));        builder.moveToElement(coursecomment).moveToElement(commenttab).click().build().perform();    }    private void addComment(String comment) {        WebElement checkProgress = driver.findElement(By.linkText("Tähtikuviot"));        checkProgress.click();        WebElement commentfield = driver.findElement(By.id("comment2"));                WebElement save = driver.findElement(By.id("save2"));        builder.moveToElement(commentfield).click().sendKeys(comment).build().perform();        builder.moveToElement(save).click().build().perform();    }    }