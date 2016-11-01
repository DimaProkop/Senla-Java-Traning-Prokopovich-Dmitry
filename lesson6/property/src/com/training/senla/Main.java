package com.training.senla;
import java.util.Properties;
import java.io.*;

/**
 * Created by prokop on 1.11.16.
 */
public class Main {
    public static void main(String[] args) {
        FileInputStream stream;
        Properties properties = new Properties();
        try {
            stream = new FileInputStream("/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson6/property/resource/config.properties");
            properties.load(stream);
            System.out.println(properties.getProperty("block.change.status"));
            System.out.println(properties.getProperty("count.registrations"));
        } catch (IOException e) {
            System.out.print("File not found");
        }
    }
}
