package com.training.senla.util.io.importer.impl;

import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.storage.Storage;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.StreamService;
import com.training.senla.util.service.impl.DataServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prokop on 16.10.16.
 */
public class ImporterImpl implements Importer {
    private DataService dataService;
    private String[] data;
    private Converter converter;

    private Map<Integer, RoomModel> roomsMap;
    private Map<Integer, ServiceModel> servicesMap;

    public ImporterImpl(StreamService streamService) {
        this.dataService = new DataServiceImpl();
        this.converter = new ConverterImpl();
        this.data = streamService.readModel();
    }


    @Override
    public void importGuests() {
        for(String line : data) {
            if(isModel(line, "G")) {
                Storage.guests.add(converter.convertStringToGuest(line, roomsMap, servicesMap));
            }
        }
    }

    @Override
    public void importRegistrations() {
        for(String line : data) {
            if(isModel(line, "T")) {
                Storage.registrations.add(converter.convertStringToRegistration(line));
            }
        }
    }

    @Override
    public void importRooms() {
        roomsMap = new HashMap<>();
        for(String line : data) {
            if (isModel(line, "R")) {
                RoomModel room = converter.convertStringToRoom(line);
                Storage.rooms.add(room);
                roomsMap.put(room.getId(), room);
            }
        }
    }

    @Override
    public void importServices() {
        servicesMap = new HashMap<>();
        for(String line : data) {
            if(isModel(line, "S")) {
                ServiceModel service = converter.convertStringToService(line);
                Storage.services.add(service);
                servicesMap.put(service.getId(), service);
            }
        }
    }

    @Override
    public void importAll() {
        List<Object> data = dataService.loadData();
        converter.convertDataToModel(data);
    }

    private boolean isModel(String string, String token) {
        String[] values = string.split(";");
        return values[0].contains(token);
    }
}
