package com.training.senla;

/**
 * Created by dmitry on 17.11.16.
 */
public class Props {
    private static boolean blockStatus;
    private static int countRecords;
    private static String pathToMainFile;

    public static boolean isBlockStatus() {
        return blockStatus;
    }

    public static void setBlockStatus(boolean blockStatus) {
        Props.blockStatus = blockStatus;
    }

    public static int getCountRecords() {
        return countRecords;
    }

    public static void setCountRecords(int countRecords) {
        Props.countRecords = countRecords;
    }

    public static String getPathToMainFile() {
        return pathToMainFile;
    }

    public static void setPathToMainFile(String pathToMainFile) {
        Props.pathToMainFile = pathToMainFile;
    }
}
