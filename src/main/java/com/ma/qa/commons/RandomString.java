package com.ma.qa.commons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Anson on 16/9/14.
 */
public class RandomString {

    public static String inInt() {
        Calendar c = Calendar.getInstance();

        String year = String.valueOf(c.get(Calendar.YEAR));
        String month = String.valueOf(c.get(Calendar.MONTH) + 1);
        String date = String.valueOf(c.get(Calendar.DATE));
        String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(c.get(Calendar.MINUTE));
        String second = String.valueOf(c.get(Calendar.SECOND));

        if (month.length() == 1) {
            month = "0" + month;
        }
        if (date.length() == 1) {
            date = "0" + date;
        }

        return year + month + date + hour + minute + second;

    }
}
