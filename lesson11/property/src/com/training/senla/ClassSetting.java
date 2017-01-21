package com.training.senla;
import java.util.*;
import java.io.*;

/**
 * Created by prokop on 1.11.16.
 */
public class ClassSetting {

    private static final String PATH_TO_CONFIG = "/home/dmitry/Senla-Java-Traning-Prokopovich-Dmitry/lesson11/property/resource/config.properties";
    private static Props props;

    public static Props getProps() {
        if(props == null) {
            Properties properties = new Properties();
            try (FileInputStream stream = new FileInputStream(PATH_TO_CONFIG)) {
                props = new Props();
                properties.load(stream);
                props.setBlockStatus(Boolean.parseBoolean(properties.getProperty("block.status")));
                props.setCountRecords(Integer.parseInt(properties.getProperty("count.records")));
                props.setPathToMainFile(properties.getProperty("path.to.main.file"));
                props.setPathToInstanceFile(properties.getProperty("path.to.instance"));
                props.setPathToFileEntity(properties.getProperty("path.to.entity.file"));
                props.setPathToFolderEntity(properties.getProperty("folder.for.entity"));
            } catch (FileNotFoundException e) {
                System.out.print("File not found");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return props;
    }


}


