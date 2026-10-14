package android_tests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalendarPage {
    private AndroidDriver driver;
    private WebDriverWait wait;
    private final By creationMenu = AppiumBy.accessibilityId("Creation menu");
    private final By selectEvent = AppiumBy.xpath("//*[@text='Event']/..");
    private final By eventTitle = AppiumBy.id("com.google.android.calendar:id/title");
    private final By saveButton = AppiumBy.id("com.google.android.calendar:id/save");

    public CalendarPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void openCreationMenu() {
        wait.until(
                ExpectedConditions.elementToBeClickable(creationMenu)
        ).click();
    }

    public void selectEvent() {
        wait.until(ExpectedConditions.elementToBeClickable(selectEvent)).click();
    }

    public void addTitle(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(eventTitle)).sendKeys(title);
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public Boolean checkIfEventExists(String title) {
        By eventCheck = AppiumBy.xpath(
                "//*[contains(@content-desc,'" + title + "')]"
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(eventCheck)).isDisplayed();
    }


}
