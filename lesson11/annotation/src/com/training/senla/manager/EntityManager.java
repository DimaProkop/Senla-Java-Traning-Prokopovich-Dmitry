package com.training.senla.manager;

import com.training.senla.template.ReadTemplate;
import com.training.senla.template.WriteTemplate;

import java.util.List;

/**
 * Created by dmitry on 17.11.16.
 */
public interface EntityManager {
    WriteTemplate analyzeArray(List objects, Class clazz);
    WriteTemplate analyzeObject(Object object, Class clazz);
    ReadTemplate analyzeObject(Class clazz);
}
