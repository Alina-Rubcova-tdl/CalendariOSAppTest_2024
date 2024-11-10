package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalendarsPage {

    protected IOSDriver driver;


    @iOSXCUITFindBy(accessibility = "calendars-button")
    private WebElement calendarsButton;

    @iOSXCUITFindBy(accessibility = "add-calendar-button")
    private WebElement addCalendarButton;

    @iOSXCUITFindBy(accessibility = "add-calendar-menubutton")
    private WebElement addCalendarMenuButton;

    @iOSXCUITFindBy(accessibility = "calendar-title-field")
    private WebElement calendarTitleField;

    @iOSXCUITFindBy(accessibility = "chevron")
    private WebElement colorSelectionChevron;

    @iOSXCUITFindBy(accessibility = "Blue")
    private WebElement blueColorOption;

    @iOSXCUITFindBy(accessibility = "Add Calendar")
    private WebElement confirmColorSelectionButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='done-button'])[2]")
    private WebElement addCalendarDoneButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='{0}']/XCUIElementTypeOther[2]")
    private WebElement calendarCell;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='checkmark.circle.fill'])[1]")
    private WebElement defaultCalendarCheckmark;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='checkmark.circle.fill'])[2]")
    private WebElement newCalendarCheckmark;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeImage[@name='info.circle'])[2]")
    private WebElement infoButtonForNewCalendar;

    @iOSXCUITFindBy(accessibility = "delete-calendar-button")
    private WebElement deleteCalendarButton;

    @iOSXCUITFindBy(accessibility = "Delete Calendar")
    private WebElement confirmDeleteCalendarButton;

    @iOSXCUITFindBy(accessibility = "done-button")
    private WebElement calendarsScreenDoneButton;

    public CalendarsPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickCalendarsButton() {
        calendarsButton.click();
    }

    public boolean isCalendarsScreenLoaded() {
        return addCalendarButton.isDisplayed();
    }

    public void clickAddCalendarButton() {
        addCalendarButton.click();
    }

    public void selectAddCalendarFromMenu() {
        addCalendarMenuButton.click();
    }

    public void enterCalendarName(String name) {
        calendarTitleField.sendKeys(name);
    }

    public void openColorSelection() {
        colorSelectionChevron.click();
    }

    public void selectBlueColor() {
        blueColorOption.click();
    }

    public void confirmColorSelection() {
        confirmColorSelectionButton.click();
    }

    public void clickAddCalendarDoneButton() {
        addCalendarDoneButton.click();
    }

    public boolean isCalendarDisplayed(String calendarName) {
        WebElement specificCalendar = driver.findElement(By.xpath("//XCUIElementTypeCell[@name='" + calendarName + "']/XCUIElementTypeOther[2]"));
        return specificCalendar.isDisplayed();
    }

    public boolean isCalendarSelected(int index) {
        WebElement checkmark = (index == 1) ? defaultCalendarCheckmark : newCalendarCheckmark;
        return checkmark.isDisplayed();
    }

    public void clickInfoButtonForCalendar(String calendarName) {
        infoButtonForNewCalendar.click();
    }

    public void clickDeleteCalendarButton() {
        deleteCalendarButton.click();
    }

    public void confirmCalendarDeletion() {
        confirmDeleteCalendarButton.click();
    }

    public boolean isOnlyOneCalendarDisplayed() {
        List<WebElement> allCells = driver.findElements(By.xpath("//XCUIElementTypeCell[contains(@name, 'Calendar')]"));
        return allCells.size() == 1;
    }

    public void clickDoneButton () {
        calendarsScreenDoneButton.click();
    }

}
