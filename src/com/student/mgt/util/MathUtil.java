package com.student.mgt.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class MathUtil {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Places must be >= 0");
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calculateMedian(List<Double> values) {
        if (values == null || values.isEmpty()) return 0.0;
        List<Double> sorted = new java.util.ArrayList<>(values);
        Collections.sort(sorted);
        int size = sorted.size();
        if (size % 2 == 1) {
            return sorted.get(size / 2);
        } else {
            return (sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2.0;
        }
    }

    public static double calculateStandardDeviation(List<Double> values) {
        if (values == null || values.size() < 2) return 0.0;
        double sum = 0.0;
        for (double val : values) sum += val;
        double mean = sum / values.size();
        double varianceSum = 0.0;
        for (double val : values) {
            varianceSum += Math.pow(val - mean, 2);
        }
        return Math.sqrt(varianceSum / values.size());
    }
}
