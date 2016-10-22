package com.training.senla.util.io.importer.impl;

import com.danco.training.TextFileWorker;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.importer.Importer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prokop on 16.10.16.
 */
public class ImporterImpl implements Importer {
    private String[] data;
    private Map<Integer, RoomModel> roomsMap;
    private Map<Integer, ServiceModel> servicesMap;
    private Facade facade;
    private Converter converter;

    public ImporterImpl() {
    }

    public ImporterImpl(Facade facade) {
        this.facade = facade;
        this.converter = new ConverterImpl();
    }

    public void loadData(TextFileWorker textFileWorker) {
        this.data = textFileWorker.readFromFile();
    }

    @Override
    public List<GuestModel> importGuests() {
        List<GuestModel> guests = new ArrayList<>();
        if(data.length == 0 || "".equals(data[0])) {
            return guests;
        }
        for(String line : data) {
            if(isModel(line, "G")) {
                guests.add(converter.convertStringToGuest(line, roomsMap, servicesMap));
            }
        }
        return guests;
    }

    @Override
    public List<RegistrationModel> importRegistrations() {
        List<RegistrationModel> registrations = new ArrayList<>();
        if(data.length == 0 || "".equals(data[0])) {
            return registrations;
        }
        for(String line : data) {
            if(isModel(line, "T")) {
                registrations.add(converter.convertStringToRegistration(line));
            }
        }
        return registrations;
    }

    @Override
    public List<RoomModel> importRooms() {
        List<RoomModel> rooms = new ArrayList<>();
        roomsMap = new HashMap<>();
        if(data.length == 0 || "".equals(data[0])) {
            return rooms;
        }
        for(String line : data) {
            if(isModel(line, "R")) {
                RoomModel room = converter.convertStringToRoom(line);
                rooms.add(room);
                roomsMap.put(room.getId(), room);
            }
        }
        return rooms;
    }

    @Override
    public List<ServiceModel> importServices() {
        List<ServiceModel> services = new ArrayList<>();
        servicesMap = new HashMap<>();
        if(data.length == 0 || "".equals(data[0])) {
            return services;
        }
        for(String line : data) {
            if(isModel(line, "S")) {
                ServiceModel service = converter.convertStringToService(line);
                services.add(service);
                servicesMap.put(service.getId(), service);
            }
        }
        return services;
    }




    private boolean isModel(String string, String token) {
        String[] values = string.split(";");
        return values[0].contains(token);
    }
}
