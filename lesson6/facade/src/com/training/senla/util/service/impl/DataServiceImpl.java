package com.training.senla.util.service.impl;

import com.training.senla.ClassSetting;
import com.training.senla.util.service.DataService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 3.11.16.
 */
public class DataServiceImpl implements DataService {

    private static final Logger LOG = LogManager.getLogger(DataServiceImpl.class);

    @Override
    public List<Object> loadData() {
        List<Object> data = new ArrayList<>();
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(ClassSetting.getPathToMainFile());
            objectInputStream = new ObjectInputStream(fileInputStream);
            try {
                data = (List<Object>) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                LOG.error(e);
            }
            fileInputStream.close();
            objectInputStream.close();
        }catch (IOException e) {
            LOG.error(e);
        }
        return data;
    }

    @Override
    public void saveData(Object object) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(ClassSetting.getPathToMainFile());
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);

            fileOutputStream.close();
            objectOutputStream.close();
        }catch (IOException e) {
            LOG.error(e);
        }
    }

    @Override
    public List<?> loadEntity() {
        return null;
    }

    @Override
    public void saveEnitty(List<?> list) {

    }
}
