
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import java.util.List;

public class Session02Tests extends TestBase{


    @Test
    void webTest() {
//        Map<String, Object> caps = driver.getSessionDetails();
//        System.out.println("Web Driver capabilities: "+ caps);
//
//        driver.get("http://google.com");
//        driver.findElement(By.cssSelector("input[name=q]")).sendKeys("Appium" + Keys.ENTER);
//        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h3"))).click();
    }

    @Test
    void driverConfigurationTest() {
        System.out.println("driver.getSettings(): " + driver.getSettings());

        driver.rotate(ScreenOrientation.LANDSCAPE);
        System.out.println("driver.getOrientation(): " + driver.getOrientation());

        driver.rotate(ScreenOrientation.PORTRAIT);
        System.out.println("driver.getOrientation(): " + driver.getOrientation());
    }

    @Test
    void immutableInteractionTest() {
        System.out.println("mobile:batteryInfo: " + driver.executeScript("mobile:batteryInfo"));
        System.out.println("mobile:deviceInfo: " + driver.executeScript("mobile:deviceInfo"));

        MobileElement appButton = (AndroidElement) driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Animation\")");

        System.out.println("getText(): " + appButton.getText());
        System.out.println("getAttribute('text'): " + appButton.getAttribute("text"));
        System.out.println("getAttribute('resource-id'): " + appButton.getAttribute("resource-id"));
        System.out.println("getAttribute('class'): " + appButton.getAttribute("class"));
        System.out.println("getAttribute('package'): " + appButton.getAttribute("package"));
        System.out.println("getAttribute('bounds'): " + appButton.getAttribute("bounds"));
        System.out.println("getAttribute('focused'): " + appButton.getAttribute("focused"));
        System.out.println("getAttribute('enabled'): " + appButton.getAttribute("enabled"));

        System.out.println("isEnabled(): " + appButton.isEnabled());
        System.out.println("isDisplayed(): " + appButton.isDisplayed());
        System.out.println("isSelected(): " + appButton.isSelected());

        System.out.println("getLocation(): " + appButton.getLocation());
        System.out.println("getSize(): " + appButton.getSize());
        System.out.println("getRect(): " + appButton.getRect().getDimension() + appButton.getRect().getPoint());

    }

    @Test
    void mutableInteractionTest() {

        //driver.startActivity(new Activity("io.appium.android.apis", "io.appium.android.apis.view.TextFields"));

        //Pseudo Scroll using TouchAction
        AndroidElement listView = (AndroidElement) driver.findElementById("android:id/list");
        Rectangle listViewRect = listView.getRect();
        TouchAction scroll = new TouchAction(driver);
        scroll.press(PointOption.point(listViewRect.width / 2, listViewRect.height + listViewRect.y - 20))
                .moveTo(PointOption.point(listViewRect.width / 2, listViewRect.y + 20))
                .release()
                .perform();

        wait.until(elementToBeClickable(
                                MobileBy.AndroidUIAutomator(
                                        "new UiSelector().text(\"Views\")"))).click();

        //Scroll using mobile scroll via script executor
        driver.executeScript("mobile: scroll", ImmutableMap.of(
                "strategy", "accessibility id", "selector", "TextFields"));

        driver.findElementByAccessibilityId("TextFields").click();

        List<WebElement> textFields = driver.findElementsByClassName("android.widget.EditText");

        for (WebElement field: textFields)
        {
            field.sendKeys("Enter some text ");
        }

        for (WebElement field: textFields)
        {
            field.sendKeys("+ some other");
        }

        for (WebElement field: textFields)
        {
            field.clear();
        }

    }
}