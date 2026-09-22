package com.student.mgt.util;

import com.student.mgt.config.AppConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppLogger {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        log("INFO", message);
    }

    public static void warn(String message) {
        log("WARN", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    private static synchronized void log(String level, String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logEntry = String.format("[%s] [%s] %s", timestamp, level, message);
        
        // Print to console
        System.out.println(logEntry);

        // Append to file log
        try (PrintWriter writer = new PrintWriter(new FileWriter(AppConfig.LOG_FILE_PATH, true))) {
            writer.println(logEntry);
        } catch (IOException ignored) {}
    }
}
