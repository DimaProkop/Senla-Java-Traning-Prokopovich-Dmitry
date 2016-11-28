package com.training.senla.manager.impl;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.annotation.CsvPropertyLink;
import com.training.senla.comparator.ColumnNumberComparator;
import com.training.senla.manager.EntityManager;
import com.training.senla.model.ReadTemplate;
import com.training.senla.model.WriteTemplate;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by dmitry on 13.11.16.
 */
public class EntityManagerImpl implements EntityManager {

    public WriteTemplate analyzeArray(List objects, Class clazz) {
        WriteTemplate params = new WriteTemplate();
        List<Object> data = new ArrayList<>();
        for (Object object : objects) {
            params = analyzeObject(object, clazz);
            List list = (List) params.getData();
            list.forEach(data::add);
        }
        params.setData(data);
        return params;
    }

    public ReadTemplate analyzeObject(Class clazz) {
        ReadTemplate params = new ReadTemplate();
        int countFields = 0;
        if (clazz.isAnnotationPresent(CsvEntity.class)) {
            CsvEntity entity = (CsvEntity) clazz.getAnnotation(CsvEntity.class);
            params.setFileName(entity.filename());
            params.setSeparator(entity.valuesSeparator());
        }
        List<Integer> orders = new ArrayList<>();
        Map<Integer,Boolean> escapeMaps = new HashMap<>();
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        for (Field field : fields) {
            if(field.isAnnotationPresent(CsvProperty.class)) {
                ++countFields;
                CsvProperty property = field.getAnnotation(CsvProperty.class);
                int columnNumber = property.columnNumber();
                boolean escape = property.escape();

                orders.add(columnNumber);
                escapeMaps.put(columnNumber, escape);
            }else if(field.isAnnotationPresent(CsvPropertyLink.class)) {
                ++countFields;
            }
        }
        params.setOrderList(orders);
        params.setEscapeMaps(escapeMaps);
        params.setCountFields(countFields);

        return params;
    }


    public WriteTemplate analyzeObject(Object object, Class clazz) {
        WriteTemplate params = new WriteTemplate();
        String nameFieldId = "id";
        if (clazz.isAnnotationPresent(CsvEntity.class)) {
            CsvEntity entity = (CsvEntity) clazz.getAnnotation(CsvEntity.class);
            params.setFileName(entity.filename());
            params.setSeparator(entity.valuesSeparator());
            nameFieldId = entity.entityId();
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
                    if (field.getType().getSimpleName().equals("List")) {
                        String list = checkList(field, keyField, object);
                        data.add(list);
                        ++countFields;
                    } else {
                        Object currentObject = field.get(object);
                        trueFiled = isTrueField(field, keyField, currentObject);
                        if (!trueFiled.equals("")) {
                            data.add(trueFiled);
                            ++countFields;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                field.setAccessible(false);
            }
        }
        params.setData(data);
        params.setCountFields(countFields);

        return params;
    }

    private String checkList(Field field, String keyField, Object object) {
        StringBuilder builder = new StringBuilder();
        try {
            List list = (List) field.get(object);
            for (int i = 0; i < list.size(); i++) {
                Object item = list.get(i);
                String answer = getValueFromList(item, keyField);
                if (!(i == list.size() - 1)) {
                    builder.append(answer);
                    builder.append(",");
                } else {
                    builder.append(answer);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private String getValueFromList(Object item, String keyField) {
        String answer = "";
        boolean flag = false;
        for (Field field : item.getClass().getDeclaredFields()) {
            if (field.getName().equals(keyField)) {
                answer = buildField(field, item);
                flag = true;
            }
        }
        if (!flag) {
            for (Field field : item.getClass().getSuperclass().getDeclaredFields()) {
                answer = buildField(field, item);
            }
        }
        return answer;
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
                    String j = "";
                    try {
                        f.setAccessible(true);
                        j = f.get(object).toString();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
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
