package com.student.mgt.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for common date operations used throughout the application.
 */
public class DateUtil {

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public static final DateTimeFormatter ISO_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    private DateUtil() {
        // Utility class, prevent instantiation
    }

    /**
     * Formats a LocalDate using the standard dd-MM-yyyy format.
     */
    public static String formatDate(LocalDate date) {
        if (date == null) return "N/A";
        return date.format(DATE_FORMAT);
    }

    /**
     * Formats a LocalDateTime using dd-MM-yyyy HH:mm:ss format.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "N/A";
        return dateTime.format(DATETIME_FORMAT);
    }

    /**
     * Calculates the number of days between two dates.
     */
    public static long daysBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) return 0;
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * Calculates age from a date of birth.
     */
    public static int calculateAge(LocalDate dateOfBirth) {
        if (dateOfBirth == null) return 0;
        return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }

    /**
     * Parses a date string in dd-MM-yyyy format to LocalDate.
     */
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;
        return LocalDate.parse(dateStr.trim(), DATE_FORMAT);
    }

    /**
     * Returns the current academic year string (e.g., "2026-2027").
     */
    public static String getCurrentAcademicYear() {
        LocalDate now = LocalDate.now();
        int year = now.getMonthValue() >= 7 ? now.getYear() : now.getYear() - 1;
        return year + "-" + (year + 1);
    }
}
