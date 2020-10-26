import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstTest {

     WebDriver browser;

    @BeforeEach
    public  void setUp() {
        System.out.println("Pre Condition");
        browser = new ChromeDriver();
    }


    @Test
    void firstTest() {
        System.out.println("Test 1");
        browser.get("http://google.com");
        browser.findElement(By.name("q")).sendKeys("Appium"+ Keys.ENTER);
    }

    @Test
    void secondTest() {
        System.out.println("Test 2");
        browser.get("http://google.com");
        browser.findElement(By.name("q")).sendKeys("Selenium"+ Keys.ENTER);
    }

    @AfterEach
     void tearDown() {
        System.out.println("Post Condition");
        browser.quit();
    }
}
