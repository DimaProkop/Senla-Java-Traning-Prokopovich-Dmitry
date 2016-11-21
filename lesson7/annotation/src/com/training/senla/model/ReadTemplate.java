package com.training.senla.model;

import java.util.List;
import java.util.Map;

/**
 * Created by dmitry on 21.11.16.
 */
public class ReadTemplate {
    private String fileName;
    private String separator;
    private List<Integer> orderList;
    private Map<Integer,Boolean> escapeMaps;
    private int countFields;

    public ReadTemplate() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public List<Integer> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Integer> orderList) {
        this.orderList = orderList;
    }

    public Map<Integer, Boolean> getEscapeMaps() {
        return escapeMaps;
    }

    public void setEscapeMaps(Map<Integer, Boolean> escapeMaps) {
        this.escapeMaps = escapeMaps;
    }

    public int getCountFields() {
        return countFields;
    }

    public void setCountFields(int countFields) {
        this.countFields = countFields;
    }
}
