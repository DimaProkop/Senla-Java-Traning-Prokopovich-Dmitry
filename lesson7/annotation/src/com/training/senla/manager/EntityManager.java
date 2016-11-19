package com.training.senla.manager;

import com.training.senla.model.Data;

import java.util.List;

/**
 * Created by dmitry on 17.11.16.
 */
public interface EntityManager {
    Data analyzeArray(List objects, Class clazz);
    Data analyzeObject(Object object, Class clazz);
    Data analyzeObject(Class clazz);
}
