package com.training.senla.manager;

import java.util.List;

/**
 * Created by dmitry on 17.11.16.
 */
public interface EntityManager {
    String[] analyzeArray(List objects, Class clazz);
    String[] analyzeObject(Object object, Class clazz);
    String[] analyzeObject(Class clazz);
}
