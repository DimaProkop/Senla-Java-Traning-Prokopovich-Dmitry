package com.training.senla.util.service.impl;

import com.training.senla.ClassSetting;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.util.service.StreamService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by prokop on 7.11.16.
 */
public class StreamServiceImpl implements StreamService {
    private static final Logger LOG = LogManager.getLogger(StreamServiceImpl.class);

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String path = FacadeImpl.getInstance().getProperty("path.to.entity.file");
    private FileWriter fileWriter;


    public StreamServiceImpl() {
    }


    @Override
    public void writeModel(String[] values) {
        try {
            fileWriter = new FileWriter(path);
            for (String line : values) {
                fileWriter.append(line);
                fileWriter.append(NEW_LINE_SEPARATOR);
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
