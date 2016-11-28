package com.training.senla.di;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitry on 17.11.16.
 */
public class DependencyInjection {
    private static Map<Class<?>, Object> mainMaps = new HashMap<>();;
    private static InstanceProps props = new InstanceProps();

    public static Object getInstance(Class<?> className) {
        Object object = mainMaps.get(className);
        if(object == null) {
            String nameInstance = props.getRealization(className.getSimpleName());
                try {
                    Class clazz = Class.forName(nameInstance);
                    Object obj = clazz.newInstance();
                    mainMaps.put(className, obj);
                    return obj;

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        return object;
    }
}
