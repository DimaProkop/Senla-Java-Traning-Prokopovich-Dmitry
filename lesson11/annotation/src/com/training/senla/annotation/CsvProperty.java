package com.training.senla.annotation;

import com.training.senla.enums.PropertyType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dmitry on 13.11.16.
 */
@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface CsvProperty {
    PropertyType propertyType();
    int columnNumber();
    boolean escape();
}
