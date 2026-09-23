package com.student.mgt.util;

import java.util.Scanner;

public class InputValidator {

    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("[WARN] Input cannot be empty. Please try again.");
        }
    }

    public static int readIntRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.printf("[WARN] Value must be between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("[WARN] Invalid integer. Please enter a valid number.");
            }
        }
    }

    public static double readDoubleRange(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(scanner.nextLine().trim());
                if (val >= min && val <= max) {
                    return val;
                }
                System.out.printf("[WARN] Value must be between %.2f and %.2f.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("[WARN] Invalid number decimal format.");
            }
        }
    }
}
