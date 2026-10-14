package android_tests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CalendarPage;

import java.time.Duration;

public class AndroidCalendarPage implements CalendarPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final By creationMenu = AppiumBy.accessibilityId("Creation menu");
    private final By selectEvent = AppiumBy.xpath("//*[@text='Event']/..");
    private final By eventTitle = AppiumBy.id("com.google.android.calendar:id/title");
    private final By saveButton = AppiumBy.id("com.google.android.calendar:id/save");

    public AndroidCalendarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickAddEventButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(creationMenu)
        ).click();
        selectEvent();
    }

    public void selectEvent() {
        wait.until(ExpectedConditions.elementToBeClickable(selectEvent)).click();
    }

    public void insertTitle(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventTitle)).sendKeys(title);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public boolean checkTitle(String title) {
        By eventCheck = AppiumBy.xpath(
                "//*[contains(@content-desc,'" + title + "')]"
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(eventCheck)).isDisplayed();
    }


}
