package tests;

import io.qameta.allure.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;

@Epic("Mobile automation calendar app testing")
@Feature("Calendars")
public class CalendarManagementTest extends DriverSetup {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: create and delete calendar")
    @Story("Successfully validating that is posible to create and delete calendar")
    @Test(testName = "Add and Delete Calendar Test")
    public void addAndDeleteCalendarTest() {
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        calendarsPage.clickCalendarsButton();
        Assert.assertTrue(calendarsPage.isCalendarsScreenLoaded(), "Calendars screen is not loaded");

        calendarsPage.clickAddCalendarButton();
        calendarsPage.selectAddCalendarFromMenu();

        String calendarName = "MyNewTestCalendar";
        calendarsPage.enterCalendarName(calendarName);
        calendarsPage.openColorSelection();
        calendarsPage.selectBlueColor();
        calendarsPage.confirmColorSelection();

        calendarsPage.clickAddCalendarDoneButton();
        Assert.assertTrue(calendarsPage.isCalendarDisplayed(calendarName), "Newly created calendar is not displayed");

        Assert.assertTrue(calendarsPage.isCalendarSelected(1), "Default calendar is not selected");
        Assert.assertTrue(calendarsPage.isCalendarSelected(2), "Newly created calendar is not selected");

        calendarsPage.clickInfoButtonForCalendar(calendarName);
        calendarsPage.clickDeleteCalendarButton();
        calendarsPage.confirmCalendarDeletion();

        Assert.assertTrue(calendarsPage.isOnlyOneCalendarDisplayed(), "Only default calendar should be displayed");

        calendarsPage.clickDoneButton();
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar screen is not loaded after deletion");
    }
}
