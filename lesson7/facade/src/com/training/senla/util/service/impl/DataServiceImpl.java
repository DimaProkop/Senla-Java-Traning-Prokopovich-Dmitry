package com.training.senla.util.service.impl;

import com.training.senla.ClassSetting;
import com.training.senla.facade.impl.FacadeImpl;
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
        String path = FacadeImpl.getInstance().getProperty("path.to.main.file");
        int i = 1 +1 ;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Object o;
            while ((o = ois.readObject()) != null) {
                data = (List<Object>) o;
            }
        }
        catch (IOException | ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
        return data;
    }

    @Override
    public void saveData(Object object) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        String path = FacadeImpl.getInstance().getProperty("path.to.main.file");
        try {
            fileOutputStream = new FileOutputStream(path);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);

        }catch (IOException e) {
            LOG.error(e);
        }
        finally {

            try {
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
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
