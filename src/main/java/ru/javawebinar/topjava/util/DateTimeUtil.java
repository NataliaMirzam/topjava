package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T> boolean isBetweenHalfOpen(LocalDateTime ldt, LocalDateTime startDateTime, LocalDateTime endDateTime, T t) {
        if (t.equals(LocalDate.class)) {
            LocalDate startDate = startDateTime.toLocalDate();
            LocalDate endDate = endDateTime.toLocalDate();
            LocalDate ld = ldt.toLocalDate();
            return ld.compareTo(startDate) >= 0 && ld.compareTo(endDate) < 0;
        } else {
            LocalTime startTime = startDateTime.toLocalTime();
            LocalTime endTime = endDateTime.toLocalTime();
            LocalTime lt = ldt.toLocalTime();
            return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
        }
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

