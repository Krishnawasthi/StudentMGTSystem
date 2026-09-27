package com.student.mgt.test;

import com.student.mgt.model.Course;
import com.student.mgt.service.FeeService;
import com.student.mgt.service.FeeServiceImpl;

public class FeeServiceTest {
    public static void main(String[] args) {
        testBaseFees();
        testMeritDiscounts();
        System.out.println("FeeServiceTest passed successfully!");
    }

    private static void testBaseFees() {
        FeeService service = new FeeServiceImpl();
        assert service.getBaseFeePerCourse(Course.JAVA) == 1200.0;
        assert service.getBaseFeePerCourse(Course.AI_ML) == 1600.0;
    }

    private static void testMeritDiscounts() {
        FeeService service = new FeeServiceImpl();
        double baseFee = service.getBaseFeePerCourse(Course.JAVA); // 1200.0
        
        double highGpaTuition = service.calculateTuition(Course.JAVA, 3.9);
        assert highGpaTuition == baseFee * 0.75; // 900.0

        double midGpaTuition = service.calculateTuition(Course.JAVA, 3.6);
        assert midGpaTuition == baseFee * 0.85;

        double lowGpaTuition = service.calculateTuition(Course.JAVA, 2.5);
        assert lowGpaTuition == baseFee;
    }
}
