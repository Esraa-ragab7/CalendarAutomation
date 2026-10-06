package android_tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import android_tests.pages.CalendarPage;

import java.net.MalformedURLException;
import java.net.URL;

public class AndroidCalendarTest {

    private AndroidDriver driver;
    private CalendarPage calendarPage;

    @BeforeMethod
    public void setUp() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("Android Emulator");
        options.setAppPackage("com.google.android.calendar");
        options.setAppActivity(
                "com.android.calendar.AllInOneActivity"
        );
        options.setNoReset(true); // don't clear Calendar's data, or it loses its synced calendars

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );
        calendarPage =  new CalendarPage(driver);
    }

    @DataProvider(name = "eventData")
    public Object[][] eventData() {
        return new Object[][]{
                {"Automation Test Event 1"},
                {"Automation Test Event 2"},
                {"Automation Test Event 3"}
        };
    }

    @Test(dataProvider = "eventData")
    public void addCalendarEventTest(String eventTitle) {
        calendarPage.openCreationMenu();
        calendarPage.selectEvent();
        calendarPage.addTitle(eventTitle);
        calendarPage.clickSaveButton();
        boolean eventExists = calendarPage.checkIfEventExists(eventTitle);
        Assert.assertTrue(
                eventExists,
                "Event was not added successfully"
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}