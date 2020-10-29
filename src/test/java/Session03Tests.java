import com.google.common.io.Files;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.NetworkSpeed;
import io.appium.java_client.android.PowerACState;
import io.appium.java_client.serverevents.CustomEvent;
import io.appium.java_client.serverevents.ServerEvents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

public class Session03Tests extends TestBase{

    @BeforeEach
    void openActivity() throws InterruptedException {
        driver.startActivity(new Activity("io.appium.android.apis", "io.appium.android.apis.ApiDemos"));
        Thread.sleep(500);
    }

    @Test
    void screenShot() {

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(scrFile,
                    new File(System.currentTimeMillis() + "MobileScreen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[Screenshot captured]");
    }

    @Test
    void logging() {

        Set<String> logTypes = driver.manage().logs().getAvailableLogTypes();

        for( String logType:logTypes) {
            LogEntries logEntries = driver.manage().logs().get(logType);
              for (LogEntry entry: logEntries)
              {
                  System.out.println("["+logType+"]" +entry);
              }
        }
    }

    @Test
    void events() throws InterruptedException {
        CustomEvent starEvent = new CustomEvent();
        starEvent.setEventName("Start Test");
        starEvent.setVendor("appium");

        CustomEvent finishEvent = new CustomEvent();
        finishEvent.setEventName("End Test");
        finishEvent.setVendor("appium");

        driver.logEvent(starEvent);

        Thread.sleep(1000);

        driver.logEvent(finishEvent);

        ServerEvents events = driver.getEvents();

        System.out.println(events);

    }

    @Test
    void appTests() {

        System.out.println(driver.isAppInstalled("io.appium.android.apis"));
        System.out.println(driver.isAppInstalled("com.my.super.app"));

        driver.runAppInBackground(Duration.ofSeconds(3));

        driver.closeApp();
        driver.launchApp();
        driver.resetApp();

        driver.activateApp("com.android.calculator2");
        driver.terminateApp("com.android.calculator2");
        System.out.println(driver.queryAppState("io.appium.android.apis"));

        Map<String, String> appStrings = driver.getAppStringMap("en", "/home/sparrow/Work/dev/appium-training/Apk_Info.apk");

        for (Map.Entry<String,String> entry : appStrings.entrySet())
            System.out.println(entry.getKey() +
                    " = " + entry.getValue());

    }


    @Test
    void power() {

        System.out.println("mobile:batteryInfo: " + driver.executeScript("mobile:batteryInfo"));
        driver.setPowerAC(PowerACState.OFF);
        driver.setPowerAC(PowerACState.ON);
        driver.setPowerCapacity(12);
        System.out.println("mobile:batteryInfo: " + driver.executeScript("mobile:batteryInfo"));
        driver.setPowerAC(PowerACState.OFF);
        driver.setPowerCapacity(78);
        System.out.println("mobile:batteryInfo: " + driver.executeScript("mobile:batteryInfo"));

    }
}
