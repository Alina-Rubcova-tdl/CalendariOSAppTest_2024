package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class DateUtils {

    public static String getFullDateWithDay(String day, String month, String year) {
        String dateString = day + " " + month + " " + year;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

        LocalDate date = LocalDate.parse(dateString, formatter);

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        String dayOfWeekFormatted = dayOfWeek.toString().charAt(0) + dayOfWeek.toString().substring(1).toLowerCase();

        return dayOfWeekFormatted + ", " + date.format(DateTimeFormatter.ofPattern("d MMMM"));
    }
}
