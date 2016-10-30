package com.training.senla.reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.StringMatchFilter;

import java.time.LocalDate;
import java.util.Date;
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

    @SuppressWarnings("deprecation")
    public static Date getDate(String message) {
        System.out.println(message);
        Date date = null;
        try {
            String[] parts = scanner.next().split("-");
            date = new Date(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }catch (Exception e) {
            LOG.error(e);
        }
        return date;
    }
}
