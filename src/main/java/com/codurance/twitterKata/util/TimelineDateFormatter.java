package com.codurance.twitterKata.util;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimelineDateFormatter {

    public static final int SECONDS_IN_A_MINUTE = 60;
    public static final int SECONDS_IN_AN_HOUR = 3600;
    public static final int SECONDS_IN_A_DAY = 86400;
    private Clock clock;

    public TimelineDateFormatter(Clock clock) {
        this.clock = clock;
    }

    public String format(LocalDateTime date) {
        long seconds = Duration.between(date, clock.now()).getSeconds();

        if (seconds < SECONDS_IN_A_MINUTE) {
            return formatSeconds(seconds);
        }

        if (seconds < SECONDS_IN_AN_HOUR) {
            return formatMinutes(seconds);
        }

        if (seconds < SECONDS_IN_A_DAY) {
            return formatHours(seconds);
        }

        return formatDays(seconds);
    }

    private String formatSeconds(long seconds) {
        return seconds <= 1 ? "just now" : Long.toString(seconds) + " seconds ago";
    }

    private String formatMinutes(long seconds) {
        long minutes = seconds / SECONDS_IN_A_MINUTE;

        return minutes == 1 ? "1 minute ago" : Long.toString(minutes) + " minutes ago";
    }

    private String formatHours(long seconds) {
        long hours = seconds / SECONDS_IN_AN_HOUR;

        return hours == 1 ? "1 hour ago" : Long.toString(hours) + " hours ago";
    }

    private String formatDays(long seconds) {
        long days = seconds / SECONDS_IN_A_DAY;

        return days == 1 ? "1 day ago" : Long.toString(days) + " days ago";
    }
}
