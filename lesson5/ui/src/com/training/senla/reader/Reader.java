package com.training.senla.reader;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Created by prokop on 24.10.16.
 */
public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(String message) {
        System.out.print(message);
        String str = scanner.next();
        return Integer.parseInt(str);
    }

    public static String getString(String message) {
        System.out.print(message);
        return scanner.next();
    }

    public static double getDouble(String message) {
        System.out.print(message);
        String str = scanner.next();
        return Double.parseDouble(str);
    }

    public static LocalDate getDate(String message) {
        System.out.println(message);

        System.out.print("Input day: ");
        String dayStr = scanner.next();
        int day = Integer.parseInt(dayStr);
        System.out.println();

        System.out.print("Input month: ");
        String monthStr = scanner.next();
        int month = Integer.parseInt(monthStr);
        System.out.println();

        System.out.print("Input month: ");
        String yearStr = scanner.next();
        int year = Integer.parseInt(yearStr);

        return LocalDate.of(year, month, day);
    }
}
