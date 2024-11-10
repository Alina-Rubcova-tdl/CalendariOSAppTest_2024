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
import util.Helpers;


import static util.DateUtils.getFullDateWithDay;

public class CalendarHomePage extends Helpers {

    protected IOSDriver driver;

    @iOSXCUITFindBy(accessibility = "Calendar")
    private RemoteWebElement calendarHomePageContainer;

    @iOSXCUITFindBy(accessibility = "DayViewContainerView")
    private RemoteWebElement dayViewContainerView;

    @iOSXCUITFindBy(accessibility = "MonthViewContainerView")
    private RemoteWebElement monthViewContainerView;

    @iOSXCUITFindBy(accessibility = "add-plus-button")
    private RemoteWebElement eventAddPlusButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='DayViewContainerView']/XCUIElementTypeButton[1]")
    private RemoteWebElement dayViewBackButton;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='MonthViewContainerView']/XCUIElementTypeButton[1]")
    private RemoteWebElement monthViewBackButton;

    public CalendarHomePage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Calendar home page is loaded")
    public boolean calendarHomePageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until(ExpectedConditions.visibilityOf(calendarHomePageContainer)).isDisplayed();
    }

    public void clickEventAddPlusButton() {
        eventAddPlusButton.click();
    }

    @Step("Switch to All Months view by navigating back")
    public void switchToAllMonthsView() {
        if (isElementVisible(dayViewContainerView)) {
            dayViewBackButton.click();
        }

        if (isElementVisible(monthViewContainerView)) {
            monthViewBackButton.click();
        }
    }

    @Step("Verify if event is added for a specific date")
    public void verifyEventForDate(String day, String month, String year) {
        WebElement monthYearButton = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='" + month + " " + year + "']"));
        monthYearButton.click();

        String fullDate = getFullDateWithDay(day, month, year);

        WebElement dateElement = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='" + fullDate + "']"));

        String eventValue = dateElement.getAttribute("value");

        Assert.assertTrue(eventValue.contains("event"), "Event is not added for " + fullDate);
    }

}