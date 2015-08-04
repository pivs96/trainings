package com.exadel;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {

    public static Date addWeekToDate(final Date date) {
        Date newDate = new Date(date.getTime());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(newDate);
        calendar.add(Calendar.DATE, 7);
        newDate.setTime(calendar.getTime().getTime());

        return newDate;
    }

    public static int getDayNumberToAdd(int beginDayOfWeek, int entryDayOfWeek) {
        return (entryDayOfWeek + (7 - beginDayOfWeek)) % 7;
    }
}
