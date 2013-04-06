package pl.mobica.hackathon.HoliSheet.misparser;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    public DateHelper() {
    }

    public String calculateWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        return String.valueOf(week);  //To change body of created methods use File | Settings | File Templates.
    }

    public String calculateDayHours(Date date, int dayOfWeek) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        return calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek ? "16" : "0";
    }
}