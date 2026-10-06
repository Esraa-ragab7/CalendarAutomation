package ios_tests.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IOSCalendarPage {
    private final WebDriver driver;
    private final By addEventButton = AppiumBy.accessibilityId("add-plus-button");
    private final By titleField = AppiumBy.accessibilityId("title-field");
    private final By saveButton = AppiumBy.accessibilityId("add-button");

    public IOSCalendarPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAddEventButton() {
        driver.findElement(addEventButton).click();
    }

    public void insertTitle(String title) {
        driver.findElement(titleField).sendKeys(title);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public boolean checkTitle(String title) {
        return driver.findElement(
                AppiumBy.accessibilityId(
                        "event-shown:" + title
                )
        ).isDisplayed();
    }
}
