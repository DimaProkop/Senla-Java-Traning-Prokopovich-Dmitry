package com.training.senla.manager;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.annotation.CsvPropertyLink;
import com.training.senla.comparator.ColumnNumberComparator;
import com.training.senla.facade.impl.FacadeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dmitry on 13.11.16.
 */
public class EntityManager {

    public String[] analyzeArray(List objects, Class clazz) {
        String[] params = new String[3];
        for (Object object : objects) {
            params = analyzeObject(object, clazz);
        }
        return params;
    }


    public String[] analyzeObject(Object object, Class clazz) {
        String[] params = new String[3];
        String fileName = "";
        String separator = "";
        String nameFieldId = "id";
        if (clazz.isAnnotationPresent(CsvEntity.class)) {
            CsvEntity entity = (CsvEntity) clazz.getAnnotation(CsvEntity.class);
            fileName = entity.filename();
            params[0] = fileName;
            separator = entity.valuesSeparator();
            params[1] = separator;
            nameFieldId = entity.entityId();
            params[2] = nameFieldId;
        }


        List<Object> data = new ArrayList<>();
        int countFields = 0;
        try {
            Field id = clazz.getSuperclass().getDeclaredField(nameFieldId);
            id.setAccessible(true);
            data.add(id.get(object));
            ++countFields;

            id.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
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
                    ++countFields;
                } else {
                    data.add(field.get(object));
                    ++countFields;
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
                    ++countFields;
                } else {
                    CsvPropertyLink link = field.getAnnotation(CsvPropertyLink.class);
                    String keyField = link.keyField();
                    String trueFiled = "";

                    //tests
                    if (field.getType().getSimpleName().equals("List")) {
                        for (Field member : ((Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]).getDeclaredFields()) {
                            String j = member.get(object).toString();
                            // FIXME: 17.11.16 problem with getter value in ArrayList (GenericType)
                            member.setAccessible(false);
                        }
                    }
                    trueFiled = isTrueField(field, keyField, object);
                    if (!trueFiled.equals("")) {
                        data.add(trueFiled);
                        ++countFields;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(false);
            }
        }
        params[3] = String.valueOf(countFields);

        return params;
    }

    private String isTrueField(Field field, String keyField, Object object) {
        String answer = "";
        boolean flag = false;
        String g = field.getType().getSimpleName();
        if (field.getType().getSimpleName().equals("List")) {
            String s = field.toString();
        }
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
            field = f.get(object).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            f.setAccessible(false);
        }
        return field;
    }
}
