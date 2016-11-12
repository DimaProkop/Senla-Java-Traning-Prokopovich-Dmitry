package com.training.senla.annotation;

import com.training.senla.enums.PropertyType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by dmitry on 12.11.16.
 */
@Target(value= ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
@interface CsvPropertyLink {
    PropertyType propertyType();
    String keyField();
}