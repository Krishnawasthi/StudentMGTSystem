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
        assert service.getBaseFeePerCourse(Course.COMPUTER_SCIENCE) == 1500.0;
        assert service.getBaseFeePerCourse(Course.INFORMATION_TECHNOLOGY) == 1400.0;
    }

    private static void testMeritDiscounts() {
        FeeService service = new FeeServiceImpl();
        double baseFee = service.getBaseFeePerCourse(Course.COMPUTER_SCIENCE); // 1500.0
        
        double highGpaTuition = service.calculateTuition(Course.COMPUTER_SCIENCE, 3.9);
        assert highGpaTuition == baseFee * 0.75; // 1125.0

        double midGpaTuition = service.calculateTuition(Course.COMPUTER_SCIENCE, 3.6);
        assert midGpaTuition == baseFee * 0.85;

        double lowGpaTuition = service.calculateTuition(Course.COMPUTER_SCIENCE, 2.5);
        assert lowGpaTuition == baseFee;
    }
}
