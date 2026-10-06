package ios_tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import ios_tests.pages.IOSCalendarPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSCalendarTest {
    private IOSDriver driver;
    private IOSCalendarPage calendarPage;

    @BeforeMethod
    public void setUp() throws MalformedURLException {

        XCUITestOptions options = new XCUITestOptions();

        options.setDeviceName("iPhone 16 Pro");
        options.setPlatformVersion("18.5");
        options.setBundleId("com.apple.mobilecal");

        driver = new IOSDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );

        calendarPage =  new IOSCalendarPage(driver);
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
        calendarPage.clickAddEventButton();
        calendarPage.insertTitle(eventTitle);
        calendarPage.clickSaveButton();
        boolean check = calendarPage.checkTitle(eventTitle);
        Assert.assertTrue(check, "Event was not added successfully");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
