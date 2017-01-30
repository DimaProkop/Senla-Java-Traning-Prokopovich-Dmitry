package com.training.senla.util.service.impl;

import com.training.senla.ClassSetting;
import com.training.senla.util.service.StreamService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.List;

/**
 * Created by prokop on 7.11.16.
 */
public class StreamServiceImpl implements StreamService {
    private static final Logger LOG = LogManager.getLogger(StreamServiceImpl.class);

    private static final String NEW_LINE_SEPARATOR = "\n";
    private FileWriter fileWriter;


    public StreamServiceImpl() {
    }


    @Override
    public void write(List objects, String fileName, String separator, int countFields) {
        String path = ClassSetting.getProps().getPathToFolderEntity()+ fileName;
        try {
            fileWriter = new FileWriter(path);
            for (int i = 0; i < objects.size(); i++) {
                String field = "";
                int iterator = 1;
                if(objects.get(i) == null) {
                    field = "null";
                }else {
                    field = objects.get(i).toString();
                }
                iterator += i;
                if(iterator % countFields == 0) {
                    fileWriter.append(field);
                    fileWriter.append(separator);
                    fileWriter.append(NEW_LINE_SEPARATOR);
                    ++iterator;
                }else {
                    fileWriter.append(field);
                    fileWriter.append(separator);
                    ++iterator;
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }
    }
}
