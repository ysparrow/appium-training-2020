import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {

    protected static WebDriverWait wait;
    protected static AndroidDriver<WebElement> driver;

    @BeforeAll
    static void setUp() throws MalformedURLException {

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
//        dc.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
//        dc.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
//        dc.setCapability(MobileCapabilityType.APP, "/home/sparrow/Work/dev/appium-training/ApiDemos-debug.apk");
//        dc.setCapability("otherApps", "/home/sparrow/Work/dev/appium-training/Apk_Info.apk");
        dc.setCapability("appPackage", "io.appium.android.apis");
        dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

        URL serverURL = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(serverURL, dc);
        wait = new WebDriverWait(driver, 4);

    }


    @AfterAll
    static void tearDown() {
        driver.quit();
    }
}
