package com.training.senla.util.service.impl;

import com.training.senla.ClassSetting;
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
    private static final String path = ClassSetting.getPathToEntityFile();
    private FileWriter fileWriter;
    private BufferedReader fileReader;


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

    @Override
    public String[] readModel() {
        String[] values = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            if(br.read() == -1) {
                values = null;
            }else {
                values = new String[br.read()];
                String line = "";
                for (int i = 0; i < values.length; i++) {
                    if((line = br.readLine()) != null) {
                        values[i] = line;
                    }
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        return values;
    }
}
