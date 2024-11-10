package tests;

import io.qameta.allure.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import util.DriverSetup;
import util.Helpers;


@Epic("Mobile automation calendar app testing")
@Feature("Event")
public class CalendarEventCreationTest extends DriverSetup {

    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Description: create new event")
    @Story("Successful creation of a new calendar event")
    @Test(testName = "Calendar test")
    public void calendarTest() {
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");
        calendarHomePage.clickEventAddPlusButton();
        Assert.assertTrue(newEventPage.newEventPageLoaded(), "New event page is not loaded");

        String eventName = Helpers.getRandomActivityName();
        Assert.assertNotNull(eventName, "Activity name could not be retrieved");

        newEventPage.enterEventName(eventName);
        Assert.assertEquals(newEventPage.getEventName(), eventName, "Event name was not entered correctly.");

        newEventPage.openStartDatePickerButton();
        newEventPage.chooseStartDate("December", "2024", "24");
        newEventPage.closeStartDatePickerButton();
        newEventPage.chooseStartHour("11", "10");

        newEventPage.openEndsDatePickerButton();
        newEventPage.chooseEndDate("December", "2024", "25");
        newEventPage.closeEndsDatePickerButton();

        newEventPage.chooseEndHour("12", "25");

        newEventPage.addTravelTime30();

        newEventPage.enableAllDay();
        Assert.assertTrue(newEventPage.isAllDayEnabled(), "All day is not Enabled");
        newEventPage.verifyOnlyOneDatePickerElement();

        newEventPage.clickAddButton();

        calendarHomePage.switchToAllMonthsView();

        calendarHomePage.verifyEventForDate("24", "December", "2024");
    }

}
