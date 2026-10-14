package pages;

public interface CalendarPage {

    void clickAddEventButton();

    void insertTitle(String title);

    void clickSaveButton();

    boolean checkTitle(String title);
}