import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstAppiumTest {

    private static WebDriverWait wait;
    private static AndroidDriver<WebElement> driver;


    @BeforeAll
    static void setUp() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
        dc.setCapability("appPackage", "com.android.calculator2");
        dc.setCapability("appActivity", "com.android.calculator2.Calculator");

        URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver<>(serverURL, dc);

        wait = new WebDriverWait(driver, 4);

    }

    @Test
    void calculatorTest() {

        driver.findElementById("com.android.calculator2:id/digit_1").click();
        driver.findElement(By.id("com.android.calculator2:id/digit_1")).click();
        driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_2")).click();
        driver.findElement(By.id("com.android.calculator2:id/digit_3")).click();

        assertEquals(driver.findElement(By.id("com.android.calculator2:id/formula")).getText(), "11+23");
        assertEquals(driver.findElement(By.id("com.android.calculator2:id/result")).getText(), "34");

        driver.findElement(By.id("com.android.calculator2:id/eq")).click();

        assertEquals(driver.findElement(By.id("com.android.calculator2:id/result")).getText(), "34");

        driver.findElement(By.id("com.android.calculator2:id/toolbar")).click();
        driver.findElementByClassName("android.widget.ImageButton").click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/title"))).click();
        driver.findElement(By.id("com.android.calculator2:id/mode")).click();

        assertEquals(driver.findElement(By.id("com.android.calculator2:id/result")).getText(), "34");

    }

    @AfterAll
    static void tearDown() {
        driver.closeApp();
    }
}
