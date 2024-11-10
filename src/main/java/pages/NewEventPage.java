package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import util.GlobalVariables;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class NewEventPage {

    protected IOSDriver driver;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeStaticText[`name == \"New\"`]")
    private RemoteWebElement newEventPageContainer;

    @iOSXCUITFindBy(accessibility = "Title")
    private RemoteWebElement eventNameField;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[1]")
    private RemoteWebElement hourOrMonthPicker;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[2]")
    private RemoteWebElement minuteOrYearPicker;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Starts']/preceding-sibling::XCUIElementTypeButton/XCUIElementTypeButton)[1]")
    private RemoteWebElement openStartDatePickerButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Date and Time Picker'] | //XCUIElementTypeButton[@name='Date and Time Picker'])[1]")
    private RemoteWebElement closeStartDatePickerButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Ends']/preceding-sibling::XCUIElementTypeButton/XCUIElementTypeButton)[1]")
    private RemoteWebElement openEndsDatePickerButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name='Date and Time Picker'] | //XCUIElementTypeButton[@name='Date and Time Picker'])[2]")
    private RemoteWebElement closeEndsDatePickerButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Starts']/preceding-sibling::XCUIElementTypeButton/XCUIElementTypeButton)[2]")
    private RemoteWebElement startsHourPickerButton;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name='Ends']/preceding-sibling::XCUIElementTypeButton/XCUIElementTypeButton)[2]")
    private RemoteWebElement endsHourPickerButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name, '20')]")
    private RemoteWebElement monthYearPicker;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeCell[@name='Travel Time']")
    private RemoteWebElement travelTimeCell;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='30 minutes']")
    private RemoteWebElement travelTime30MinutesButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch[@name='All-day']")
    private RemoteWebElement allDaySwitch;

    @iOSXCUITFindBy(accessibility = "add-button")
    private RemoteWebElement addButton;

    public NewEventPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("New event page is loaded")
    public boolean newEventPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).
                until(ExpectedConditions.visibilityOf(newEventPageContainer)).isDisplayed();
    }

    @Step("Enter event name: {0}")
    public void enterEventName(String eventName) {
        eventNameField.sendKeys(eventName);
    }

    @Step("Get event name from the event name field")
    public String getEventName() {
        return eventNameField.getText();
    }

    @Step("Open the Start date picker button")
    public void openStartDatePickerButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(openStartDatePickerButton)).click();
    }

    @Step("Close the Start date picker button")
    public void closeStartDatePickerButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(closeStartDatePickerButton)).click();
    }

    @Step("Open the Ends date picker button")
    public void openEndsDatePickerButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(openEndsDatePickerButton)).click();
    }

    @Step("Close the Ends date picker button")
    public void closeEndsDatePickerButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(closeEndsDatePickerButton)).click();
    }

    @Step("Click the date picker button for current month and year")
    public void clickDatePickerButtonForMonthAndYear() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        String currentMonthYear = dateFormat.format(calendar.getTime());

        String datePickerButtonXpath = "//XCUIElementTypeStaticText[@name='" + currentMonthYear + "']";
        WebElement datePickerButton = driver.findElement(By.xpath(datePickerButtonXpath));
        datePickerButton.click();
    }

    @Step("Start month {0}, year {1}, and date {2} is chosen")
    public void chooseStartDate(String month, String year, String date) {
        clickDatePickerButtonForMonthAndYear();

        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(hourOrMonthPicker))
                .sendKeys(month);
        minuteOrYearPicker.sendKeys(year);

        String datePickerButtonXpath = "//XCUIElementTypeStaticText[@name='" + month + " " + year + "']";
        WebElement datePickerButton = driver.findElement(By.xpath(datePickerButtonXpath));
        datePickerButton.click();

        WebElement dateElement = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='" + date + "']"));
        dateElement.click();

    }

    @Step("Ends month {0}, year {1}, and date {2} is chosen")
    public void chooseEndDate(String month, String year, String date) {
        monthYearPicker.click();

        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(hourOrMonthPicker))
                .sendKeys(month);
        minuteOrYearPicker.sendKeys(year);

        String datePickerButtonXpath = "//XCUIElementTypeStaticText[@name='" + month + " " + year + "']";
        WebElement datePickerButton = driver.findElement(By.xpath(datePickerButtonXpath));
        datePickerButton.click();

        WebElement dateElement = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='" + date + "']"));
        dateElement.click();
    }

    @Step("Start hour {0} and minutes {1} is chosen")
    public void chooseStartHour(String hour, String minutes) {
        startsHourPickerButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).
                until(ExpectedConditions.visibilityOf(hourOrMonthPicker)).sendKeys(hour);
        minuteOrYearPicker.sendKeys(minutes);
    }

    @Step("Ends hour {0} and minutes {1} is chosen")
    public void chooseEndHour(String hour, String minutes) {
        endsHourPickerButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).
                until(ExpectedConditions.visibilityOf(hourOrMonthPicker)).sendKeys(hour);
        minuteOrYearPicker.sendKeys(minutes);
    }

    @Step("Add travel time of 30 minutes")
    public void addTravelTime30() {
        travelTimeCell.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(travelTime30MinutesButton)).click();
    }

    @Step("Enable All-day")
    public void enableAllDay() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.elementToBeClickable(allDaySwitch))
                .click();
    }

    @Step("Verify that only one Date Picker element is visible for Starts and Ends and no time picker for Ends")
    public void verifyOnlyOneDatePickerElement() {
        List<WebElement> startDatePickers = driver.findElements(By.xpath("//XCUIElementTypeButton[@name='Date Picker']"));
        Assert.assertEquals(startDatePickers.size(), 1, "More than one Start Date Picker found");

        List<WebElement> endDatePickers = driver.findElements(By.xpath("//XCUIElementTypeOther[@name='Date Picker']"));
        Assert.assertEquals(endDatePickers.size(), 1, "More than one End Date Picker found");
    }

    @Step("Click on Add button")
    public void clickAddButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout)
                .until(ExpectedConditions.visibilityOf(addButton)).click();
    }

    public boolean isAllDayEnabled() {
        return allDaySwitch.getAttribute("value").equals("1");
    }

}
