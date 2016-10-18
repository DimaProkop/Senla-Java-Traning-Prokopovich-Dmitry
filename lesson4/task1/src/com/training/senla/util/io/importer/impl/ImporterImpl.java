package com.training.senla.util.io.importer.impl;

import com.danco.training.TextFileWorker;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.io.importer.Importer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ImporterImpl implements Importer {

    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson4/task1/resource/main.txt";
    private TextFileWorker textFileWorker = new TextFileWorker(FILE_PATH);
    private String[] data;

    private Facade facade;
    private Converter converter;

    public ImporterImpl(Facade facade) {
        this.facade = facade;
        this.data = textFileWorker.readFromFile();
    }

    @Override
    public List<GuestModel> importGuests() {
        List<GuestModel> guests = new ArrayList<>();
        for(String line : data) {
            if(isGuest(line)) {
                guests.add(converter.convertStringToGuest(line, facade));
            }
        }
        return guests;
    }

    @Override
    public List<RegistrationModel> importRegistrations() {
        List<RegistrationModel> registrations = new ArrayList<>();
        for(String line : data) {
            if(isRegistation(line)) {
                registrations.add(converter.convertStringToRegistration(line));
            }
        }
        return registrations;
    }

    @Override
    public List<RoomModel> importRooms() {
        List<RoomModel> rooms = new ArrayList<>();
        for(String line : data) {
            if(isRoom(line)) {
                rooms.add(converter.convertStringToRoom(line, facade));
            }
        }
        return rooms;
    }

    @Override
    public List<ServiceModel> importServices() {
        List<ServiceModel> services = new ArrayList<>();
        for(String line : data) {
            if(isService(line)) {
                services.add(converter.convertStringToService(line));
            }
        }
        return services;
    }




    private boolean isGuest(String string) {
        String[] values = string.split(";");
        for (String value : values) {
            if (value.replace("[", "").replace("]", "").replace(", ", "").charAt(0) == 'G') {
                return true;
            }
        }
        return false;
    }

    private boolean isRoom(String string) {
        String[] values = string.split(";");
        for (String value : values) {
            if (value.replace("[", "").replace("]", "").replace(", ", "").charAt(0) == 'R') {
                return true;
            }
        }
        return false;
    }

    private boolean isService(String string) {
        String[] values = string.split(";");
        for (String value : values) {
            if (value.replace("[", "").replace("]", "").replace(", ", "").charAt(0) == 'S') {
                return true;
            }
        }
        return false;
    }

    private boolean isRegistation(String string) {
        String[] values = string.split(";");
        for (String value : values) {
            if (value.replace("[", "").replace("]", "").replace(", ", "").charAt(0) == 'T') {
                return true;
            }
        }
        return false;
    }
}
