package com.sharad.covid.alert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeAgo {

    public static String getTimeAgo(Date past) {

        Date date = new Date();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(date.getTime() - past.getTime());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(date.getTime() - past.getTime());
        long hours = TimeUnit.MILLISECONDS.toHours(date.getTime() - past.getTime());

        if (seconds < 60)
            return "Just now";
        else if (minutes < 60)
            return minutes + " minutes ago";
        else if (hours < 24)
            return hours + " hour ago";
        else
            return new SimpleDateFormat("dd/MM/yy, hh:mm a").format(past);
    }
}
