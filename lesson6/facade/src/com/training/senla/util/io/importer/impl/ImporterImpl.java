package com.training.senla.util.io.importer.impl;

import com.training.senla.ClassSetting;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.storage.Storage;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.StreamService;
import com.training.senla.util.service.impl.DataServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prokop on 16.10.16.
 */
public class ImporterImpl implements Importer {
    private static final Logger LOG = LogManager.getLogger(ImporterImpl.class);

    private DataService dataService;
    private Converter converter;

    private Map<Integer, RoomModel> roomsMap;
    private Map<Integer, ServiceModel> servicesMap;

    public ImporterImpl() {
        this.dataService = new DataServiceImpl();
        this.converter = new ConverterImpl();
        initMaps();
    }

    @Override
    public void importModel(String token) {
        try (BufferedReader br = new BufferedReader(new FileReader(ClassSetting.getPathToEntityFile()))){
            String line = "";
            while ((line = br.readLine()) != null) {
                switch (token) {
                    case "G":
                        if(isModel(line, token)) {
                            GuestModel guest = converter.convertStringToGuest(line, roomsMap, servicesMap);
                            if(guest.getId() == FacadeImpl.getInstance().getGuest(guest.getId()).getId()) {
                                FacadeImpl.getInstance().updateGuest(guest);
                            }else {
                                Storage.guests.add(guest);
                            }
                        }
                        break;
                    case "R":
                        if (isModel(line, token)) {
                            RoomModel room = converter.convertStringToRoom(line);
                            if(room.getId() == FacadeImpl.getInstance().getRoom(room.getId()).getId()) {
                                FacadeImpl.getInstance().updateRoom(room);
                            }else {
                                Storage.rooms.add(room);
                                roomsMap.put(room.getId(), room);
                            }
                        }
                        break;
                    case "T":
                        if(isModel(line, token)) {
                            RegistrationModel registration = converter.convertStringToRegistration(line);
                            if(registration.getId() == FacadeImpl.getInstance().getRegistration(registration.getId()).getId()) {
                                FacadeImpl.getInstance().updateRegistration(registration);
                            }else {
                                Storage.registrations.add(registration);
                            }
                        }
                        break;
                    case "S":
                        if(isModel(line, token)) {
                            ServiceModel service = converter.convertStringToService(line);
                            if(service.getId() == FacadeImpl.getInstance().getService(service.getId()).getId()) {
                                FacadeImpl.getInstance().updateService(service);
                            }else {
                                Storage.services.add(service);
                                servicesMap.put(service.getId(), service);
                            }
                        }
                        break;
                }
            }
        }catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importAll() {
        List<Object> data = dataService.loadData();
        converter.convertDataToModel(data);
    }

    //initialize maps for successfully update the object
    private void initMaps() {
        servicesMap = new HashMap<>();
        roomsMap = new HashMap<>();
        for (ServiceModel service : Storage.services) {
            servicesMap.put(service.getId(), service);
        }

        for (RoomModel room : Storage.rooms) {
            roomsMap.put(room.getId(), room);
        }
    }

    private boolean isModel(String string, String token) {
        String[] values = string.split(";");
        return values[0].contains(token);
    }
}
