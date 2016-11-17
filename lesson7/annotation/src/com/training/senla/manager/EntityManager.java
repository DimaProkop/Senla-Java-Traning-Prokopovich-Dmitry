package com.training.senla.manager;

import java.util.List;

/**
 * Created by dmitry on 17.11.16.
 */
public interface EntityManager {
    Object[] analyzeArray(List objects, Class clazz);
    Object[] analyzeObject(Object object, Class clazz);
    Object[] analyzeObject(Class clazz);
}
