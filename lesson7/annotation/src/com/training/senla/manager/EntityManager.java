package com.training.senla.manager;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.annotation.CsvPropertyLink;
import com.training.senla.comparator.ColumnNumberComparator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dmitry on 13.11.16.
 */
public class EntityManager {
    public void analyz(Object object) {
        Class smthClass = object.getClass();

        String fileName = "";
        String separator = "";
        String nameFieldId = "id";
        if (smthClass.isAnnotationPresent(CsvEntity.class)) {
            CsvEntity entity = (CsvEntity) smthClass.getAnnotation(CsvEntity.class);
            fileName = entity.filename();
            separator = entity.valuesSeparator();
            nameFieldId = entity.entityId();
        } else {
            System.out.println("This class can not be saved");
        }
        List<Object> data = new ArrayList<>();
        try {
            Field id = object.getClass().getSuperclass().getDeclaredField(nameFieldId);
            id.setAccessible(true);
            data.add(id.get(object));

            id.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        List<Field> fields = Arrays.asList(object.getClass().getDeclaredFields());
        List<Field> simpleFields = fields.stream()
                .filter(x -> x.isAnnotationPresent(CsvProperty.class))
                .sorted(new ColumnNumberComparator())
                .collect(Collectors.toList());
        for (Field field : simpleFields) {
            try {
                CsvProperty property = field.getAnnotation(CsvProperty.class);
                field.setAccessible(true);
                if (!property.escape()) {
                    data.add(null);
                } else {
                    data.add(field.get(object));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(false);
            }
        }

        List<Field> compositeFields = fields.stream()
                .filter(x -> x.isAnnotationPresent(CsvPropertyLink.class))
                .collect(Collectors.toList());
        for (Field field : compositeFields) {
            try {
                field.setAccessible(true);
                if (field.get(object) == null) {
                    data.add(null);
                } else {
                    CsvPropertyLink link = field.getAnnotation(CsvPropertyLink.class);
                    String keyField = link.keyField();
                    String trueFiled = isTrueField(field, keyField, object);
                    if (!trueFiled.equals("")) {
                        data.add(trueFiled);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(false);
            }
        }
    }

    private String isTrueField(Field field, String keyField, Object object) {
        String answer = "";
        boolean flag = false;
        for (Field f : field.getType().getDeclaredFields()) {
            if (keyField.equals(f.getName())) {
                answer = buildField(f, object);
                flag = true;
            }
        }
        if (!flag) {
            for (Field f : field.getType().getSuperclass().getDeclaredFields()) {
                if (keyField.equals(f.getName())) {
                    answer = buildField(f, object);
                }
            }
        }
        return answer;
    }

    private String buildField(Field f, Object object) {
        String field = "";
        try {
            f.setAccessible(true);
            f.get(object);
            field = f.get(object).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }finally {
            f.setAccessible(false);
        }
        return field;
    }
}
