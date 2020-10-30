import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class FirstIOSTest {

    protected static WebDriverWait wait;
    protected static IOSDriver<WebElement> driver;

    @BeforeAll
    static void setUp() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.1");
        //dc.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        dc.setCapability("bundleId", "io.appium.TestApp");

        URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new IOSDriver<>(serverURL, dc);
        wait = new WebDriverWait(driver, 4);

    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @Test
    void name() {

        driver.findElementByAccessibilityId("IntegerA").sendKeys("23");
        driver.findElementByAccessibilityId("IntegerB").sendKeys("44");
        driver.findElementByAccessibilityId("Compute Sum").click();
        Assertions.assertEquals("67", driver.findElementByAccessibilityId("Answer").getText());

    }
}
