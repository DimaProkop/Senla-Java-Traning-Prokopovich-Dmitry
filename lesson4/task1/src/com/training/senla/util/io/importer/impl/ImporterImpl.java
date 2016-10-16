package com.training.senla.util.io.importer.impl;

import com.danco.training.TextFileWorker;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.io.importer.Importer;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ImporterImpl implements Importer {

    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson4/task1/resource/main.txt";
    private TextFileWorker textFileWorker = new TextFileWorker(FILE_PATH);


    @Override
    public List<GuestModel> importGuests() {

        return null;
    }

    @Override
    public List<RegistrationModel> importRegistrations() {
        return null;
    }

    @Override
    public List<RoomModel> importRooms() {
        return null;
    }

    @Override
    public List<ServiceModel> importServices() {
        return null;
    }
}
