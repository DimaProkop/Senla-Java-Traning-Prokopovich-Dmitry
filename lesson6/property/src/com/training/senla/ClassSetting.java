package com.training.senla;
import java.util.Properties;
import java.io.*;

/**
 * Created by prokop on 1.11.16.
 */
public class ClassSetting {

    private static String PATH_TO_CONFIG = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson6/property/resource/config.properties";
    private static FileInputStream stream = null;
    private static Properties properties = new Properties();

    public static void init() {
        try {
            stream = new FileInputStream(PATH_TO_CONFIG);
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        }
    }

    public static boolean setupRoomStatus() {
        boolean answer = true;
        try {
            properties.load(stream);
            answer = Boolean.parseBoolean(properties.getProperty("block.change.status"));
        } catch (IOException e) {
            System.out.print("File not found");
        }
        return answer;
    }

    public static int setupRecordsRooms() {
        int answer = 100;
        try {
            properties.load(stream);
            answer = Integer.parseInt(properties.getProperty("count.registrations"));
        } catch (IOException e) {
            System.out.print("File not found");
        }
        return answer;
    }

    public static String getPathToMainFile() {
        String answer = "";
        try {
            properties.load(stream);
            answer = String.valueOf(properties.getProperty("path.to.main.file"));
        } catch (IOException e) {
            System.out.print("File not found");
        }
        return answer;
    }

    public static String getPathToEntityFile() {
        String answer = "";
        try {
            properties.load(stream);
            answer = String.valueOf(properties.getProperty("path.to.entity.file"));
        } catch (IOException e) {
            System.out.print("File not found");
        }
        return answer;
    }
}
