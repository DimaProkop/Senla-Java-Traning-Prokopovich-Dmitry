package com.training.senla;
import java.util.*;
import java.io.*;

/**
 * Created by prokop on 1.11.16.
 */
public class ClassSetting {

    private final String PATH_TO_CONFIG = "/home/dmitry/Senla-Java-Traning-Prokopovich-Dmitry/lesson7/property/resource/config.properties";
    private Map<String, String> propsHolder = null;

    public ClassSetting() {
        this.propsHolder = new HashMap<>();
        this.init();
    }

    private void init() {
        Properties properties = new Properties();
        try (FileInputStream stream = new FileInputStream(this.PATH_TO_CONFIG)){
            properties.load(stream);
            for (Object obj : properties.keySet()) {
                String key = String.valueOf(obj);
                this.propsHolder.put(key, properties.getProperty(key));
            }

        } catch (FileNotFoundException e) {
            System.out.print("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getPropsHolder() {
        return propsHolder;
    }

    public void setPropsHolder(Map<String, String> propsHolder) {
        this.propsHolder = propsHolder;
    }
}
