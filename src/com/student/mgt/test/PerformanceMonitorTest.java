package com.student.mgt.test;

import com.student.mgt.util.PerformanceMonitor;

public class PerformanceMonitorTest {
    public static void main(String[] args) {
        testRunnableMeasurement();
        testCallableMeasurement();
        System.out.println("PerformanceMonitorTest passed successfully!");
    }

    private static void testRunnableMeasurement() {
        PerformanceMonitor.runAndMeasure("Test Runnable", () -> {
            try { Thread.sleep(10); } catch (InterruptedException ignored) {}
        });
    }

    private static void testCallableMeasurement() {
        try {
            String result = PerformanceMonitor.callAndMeasure("Test Callable", () -> "Success");
            assert "Success".equals(result);
        } catch (Exception e) {
            assert false : "Unexpected exception: " + e.getMessage();
        }
    }
}
