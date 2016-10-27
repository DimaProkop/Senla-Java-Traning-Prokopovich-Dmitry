package com.training.senla.reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.StringMatchFilter;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by prokop on 24.10.16.
 */
public class Reader {
    private static final Logger LOG = LogManager.getLogger(Reader.class);
    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(String message) {
        int number = 0;
        boolean flag = true;
        try {
            while (flag) {
                try {
                    System.out.print(message);
                    String str = scanner.next();
                    number = Integer.parseInt(str);
                    flag = false;
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("Incorrect!");
                }
            }
        } catch (Exception e) {
            LOG.error(e);
        }
        return number;
    }

    public static String getString(String message) {
        String string = null;
        boolean flag = true;
        try {
            while (flag) {
                try {
                    System.out.print(message);
                    string = scanner.next();
                    flag = false;
                }catch (InputMismatchException e) {
                    System.out.println("Incorrect!");
                }
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return string;
    }

    public static double getDouble(String message) {
        double number = 0;
        boolean flag = true;
        try {
            while (flag) {
                try {
                    System.out.print(message);
                    String str = scanner.next();
                    number = Double.parseDouble(str);
                    flag = false;
                }catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Incorrect!");
                }
            }

        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return number;
    }

    public static LocalDate getDate(String message) {
        System.out.println(message);
        int day = 0;
        int month = 0;
        int year = 0;
        LocalDate date = null;
        try {
            day = getPartOfDate("Input day: ");
            month = getPartOfDate("Input month: ");
            year = getPartOfDate("Input year: ");
            date = LocalDate.of(year, month, day);
        }catch (Exception e) {
            LOG.error(e);
        }
        return date;
    }

    private static int getPartOfDate(String message) {
        int partDate = 0;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(message);
                String part = scanner.next();
                partDate = Integer.parseInt(part);
                flag = false;
            }catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Incorrect!");
            }
        }
        return partDate;
    }
}
