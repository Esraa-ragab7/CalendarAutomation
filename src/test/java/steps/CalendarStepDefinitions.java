package steps;

import android_tests.pages.AndroidCalendarPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ios_tests.pages.IOSCalendarPage;
import org.testng.Assert;
import pages.CalendarPage;

import java.net.MalformedURLException;
import java.net.URL;

public class CalendarStepDefinitions {
    private AppiumDriver driver;
    private CalendarPage calendarPage;
    private String platform = "android";

    @Before
    public void setUp() throws MalformedURLException {
        if (platform.equalsIgnoreCase("ios")) {
            XCUITestOptions options = new XCUITestOptions();

            options.setDeviceName("iPhone 16 Pro");
            options.setPlatformVersion("18.5");
            options.setBundleId("com.apple.mobilecal");

            driver = new IOSDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );

            calendarPage = new IOSCalendarPage(driver);
        } else if (platform.equalsIgnoreCase("android")) {

            UiAutomator2Options options = new UiAutomator2Options();

            options.setDeviceName("Android Emulator");
            options.setAppPackage("com.google.android.calendar");
            options.setAppActivity("com.android.calendar.AllInOneActivity");
            options.setNoReset(true);

            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    options
            );

            calendarPage = new AndroidCalendarPage(driver);
        }

    }

    @Given("the Calendar application is open")
    public void the_calendar_application_is_open() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The Calendar application is open");
    }

    @When("I create a new event with title {string}")
    public void i_create_a_new_event_with_title(String eventTitle) {
        calendarPage.clickAddEventButton();
        calendarPage.insertTitle(eventTitle);
        calendarPage.clickSaveButton();
    }

    @Then("the event {string} should be displayed successfully")
    public void the_event_should_be_displayed_successfully(String eventTitle) {
        boolean eventExists = calendarPage.checkTitle(eventTitle);
        Assert.assertTrue(eventExists, "Event was not added successfully");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
