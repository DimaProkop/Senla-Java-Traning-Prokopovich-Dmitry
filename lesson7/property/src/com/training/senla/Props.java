package com.training.senla;

/**
 * Created by dmitry on 17.11.16.
 */
public class Props {
    private static boolean blockStatus;
    private static int countRecords;
    private static String pathToMainFile;
    private static String pathToInstanceFile;
    private static String pathToFolderEntity;
    private static String pathToFileEntity;

    public static String getPathToInstanceFile() {
        return pathToInstanceFile;
    }

    public static void setPathToInstanceFile(String pathToInstanceFile) {
        Props.pathToInstanceFile = pathToInstanceFile;
    }

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

    public static String getPathToFolderEntity() {
        return pathToFolderEntity;
    }

    public static void setPathToFolderEntity(String pathToFolderEntity) {
        Props.pathToFolderEntity = pathToFolderEntity;
    }

    public static String getPathToFileEntity() {
        return pathToFileEntity;
    }

    public static void setPathToFileEntity(String pathToFileEntity) {
        Props.pathToFileEntity = pathToFileEntity;
    }
}
