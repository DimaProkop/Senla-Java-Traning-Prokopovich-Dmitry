package com.training.senla.comparator;
import com.training.senla.annotation.CsvProperty;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * Created by dmitry on 13.11.16.
 */
public class ColumnNumberComparator implements Comparator<Field> {
    @Override
    public int compare(Field field, Field t1) {
        int number = field.getAnnotation(CsvProperty.class).columnNumber();
        int number1 = t1.getAnnotation(CsvProperty.class).columnNumber();
        return Integer.compare(number, number1);
    }
}
