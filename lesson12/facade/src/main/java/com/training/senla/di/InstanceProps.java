package com.training.senla.di;

import com.training.senla.ClassSetting;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dmitry on 17.11.16.
 */
public class InstanceProps {
    private Properties properties = new Properties();

    public InstanceProps() {
        try (FileInputStream stream = new FileInputStream(ClassSetting.getProps().getPathToInstanceFile())) {
            properties.load(stream);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getRealization(String className) {
        return properties.getProperty(className);
    }
}
