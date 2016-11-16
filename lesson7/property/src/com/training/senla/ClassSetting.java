package com.training.senla;
import java.util.*;
import java.io.*;

/**
 * Created by prokop on 1.11.16.
 */
public class ClassSetting {

    private final String PATH_TO_CONFIG = "/home/dmitry/Senla-Java-Traning-Prokopovich-Dmitry/lesson7/property/resource/config.properties";

    public ClassSetting() {
        this.init();
    }

    private void init() {
        Properties properties = new Properties();
        try (FileInputStream stream = new FileInputStream(this.PATH_TO_CONFIG)) {
            properties.load(stream);
            Props.setBlockStatus(Boolean.parseBoolean(properties.getProperty("block.status")));
            Props.setCountRecords(Integer.parseInt(properties.getProperty("count.records")));
            Props.setPathToMainFile(properties.getProperty("path.to.main.file"));
        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


