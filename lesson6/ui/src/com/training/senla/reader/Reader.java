package com.training.senla.reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.StringMatchFilter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by prokop on 24.10.16.
 */
public class Reader {
    private static final Logger LOG = LogManager.getLogger(Reader.class);
    private static Scanner scanner = new Scanner(System.in);
    private static final String DATE_SEPARATOR = "/";

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

    @SuppressWarnings("deprecation")
    public static Date getDate(String message) {
        System.out.println(message);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            String day = String.valueOf(partDate("Input day: "));
            String month = String.valueOf(partDate("Input month: "));
            String year = String.valueOf(partDate("Input year: "));
            String finalDate = successDate(day, month, year);
            date = formatter.parse(finalDate);
        }catch (Exception e) {
            LOG.error(e);
        }
        return date;
    }

    private static int partDate(String message) {
        int part = 0;
        boolean flag = true;
        try {
            while (flag) {
                try {
                    System.out.print(message);
                    String line = scanner.next();
                    part = Integer.parseInt(line);
                    flag = false;
                }catch (InputMismatchException | NumberFormatException e) {
                    System.out.println("Incorrect!");
                }
            }

        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return part;
    }

    private static String successDate(String day, String month, String year) {
        StringBuilder builder = new StringBuilder();
        builder.append(day);
        builder.append(DATE_SEPARATOR);
        builder.append(month);
        builder.append(DATE_SEPARATOR);
        builder.append(year);
        return builder.toString();
    }
}
