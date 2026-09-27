package com.student.mgt.test;

import com.student.mgt.util.MathUtil;

import java.util.Arrays;
import java.util.List;

public class MathUtilTest {
    public static void main(String[] args) {
        System.out.println("Running MathUtil Unit Verification Tests...");

        // Test rounding
        double rounded = MathUtil.round(3.8567, 2);
        assert rounded == 3.86 : "Rounding failed, expected 3.86 got " + rounded;
        System.out.println("[PASS] Rounding test passed");

        // Test median (odd count)
        List<Double> oddList = Arrays.asList(3.0, 4.0, 3.5);
        double medianOdd = MathUtil.calculateMedian(oddList);
        assert medianOdd == 3.5 : "Odd median failed, got " + medianOdd;
        System.out.println("[PASS] Odd median test passed");

        // Test median (even count)
        List<Double> evenList = Arrays.asList(3.0, 4.0, 3.5, 3.8);
        double medianEven = MathUtil.calculateMedian(evenList);
        assert Math.abs(medianEven - 3.65) < 0.01 : "Even median failed, got " + medianEven;
        System.out.println("[PASS] Even median test passed");

        System.out.println("All MathUtil tests passed successfully!");
    }
}
