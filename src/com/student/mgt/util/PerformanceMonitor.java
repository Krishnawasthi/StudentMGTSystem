package com.student.mgt.util;

import java.util.concurrent.Callable;

/**
 * Utility for measuring and logging execution times of procedures and functions.
 */
public class PerformanceMonitor {

    public static void runAndMeasure(String taskName, Runnable task) {
        long start = System.nanoTime();
        try {
            task.run();
        } finally {
            long durationMs = (System.nanoTime() - start) / 1_000_000;
            AppLogger.info("[Performance] Task '" + taskName + "' executed in " + durationMs + " ms");
        }
    }

    public static <T> T callAndMeasure(String taskName, Callable<T> task) throws Exception {
        long start = System.nanoTime();
        try {
            return task.call();
        } finally {
            long durationMs = (System.nanoTime() - start) / 1_000_000;
            AppLogger.info("[Performance] Callable '" + taskName + "' executed in " + durationMs + " ms");
        }
    }
}
