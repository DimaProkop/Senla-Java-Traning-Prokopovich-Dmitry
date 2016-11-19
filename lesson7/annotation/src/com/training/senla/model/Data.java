package com.training.senla.model;

import java.util.List;

/**
 * Created by dmitry on 19.11.16.
 */
public class Data {
    private String fileName;
    private String separator;
    private List<Object> data;
    private int countFields;

    public Data() {
    }

    public Data(String fileName, String separator, List<Object> data, int countFields) {
        this.fileName = fileName;
        this.separator = separator;
        this.data = data;
        this.countFields = countFields;
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

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public int getCountFields() {
        return countFields;
    }

    public void setCountFields(int countFields) {
        this.countFields = countFields;
    }
}
