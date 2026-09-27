package com.student.mgt.util;

/**
 * Utility class to query runtime system information and JVM metrics.
 */
public class SystemInfoUtil {

    public static long getUsedMemoryBytes() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static String getUsedMemoryFormatted() {
        long bytes = getUsedMemoryBytes();
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }

    public static int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static void printSystemSummary() {
        System.out.println("=== System Information Summary ===");
        System.out.println("OS Name: " + getOsName());
        System.out.println("Java Version: " + getJavaVersion());
        System.out.println("Processors: " + getAvailableProcessors());
        System.out.println("Used Memory: " + getUsedMemoryFormatted());
        System.out.println("==================================");
    }
}
