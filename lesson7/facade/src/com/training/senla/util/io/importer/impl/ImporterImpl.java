package com.training.senla.util.io.importer.impl;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.repository.RoomModelRepository;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.repository.impl.GuestModelRepositoryImpl;
import com.training.senla.repository.impl.RegistrationModelRepositoryImpl;
import com.training.senla.repository.impl.RoomModelRepositoryImpl;
import com.training.senla.repository.impl.ServiceModelRepositoryImpl;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.service.DataService;
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
    private static final String path = FacadeImpl.getInstance().getProperty("path.to.entity.file");

    private Converter converter;

    private Map<Integer, RoomModel> roomsMap;
    private Map<Integer, ServiceModel> servicesMap;

    public ImporterImpl(List<ServiceModel> services, List<RoomModel> rooms) {
        this.converter = new ConverterImpl();
        initMaps(services, rooms);
    }

    @Override
    public void importGuests(List<GuestModel> guests) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isModel(line, GuestModel.ENTITY_TOKEN)) {
                    GuestModel guest = converter.convertStringToGuest(line, roomsMap, servicesMap);
                    if (guests.contains(guest)) {
                        guests.set(guest.getId(), guest);
                    } else {
                        guests.add(guest);
                    }
                }
            }
            GuestModelRepository repository = new GuestModelRepositoryImpl(guests);
        }catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importRooms(List<RoomModel> rooms) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isModel(line, RoomModel.ENTITY_TOKEN)) {
                    RoomModel room = converter.convertStringToRoom(line);
                    if(room.getId() == FacadeImpl.getInstance().getRoom(room.getId()).getId()) {
                        FacadeImpl.getInstance().updateRoom(room);
                    }else {
                        rooms.add(room);
                        roomsMap.put(room.getId(), room);
                    }
                }
            }
            RoomModelRepository repository = new RoomModelRepositoryImpl(rooms);
        }catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importRegistrations(List<RegistrationModel> registrations) {

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = "";
            while ((line = br.readLine()) != null) {
                if(isModel(line, RegistrationModel.ENTITY_TOKEN)) {
                    RegistrationModel registration = converter.convertStringToRegistration(line);
                    if(registration.getId() == FacadeImpl.getInstance().getRegistration(registration.getId()).getId()) {
                        FacadeImpl.getInstance().updateRegistration(registration);
                    }else {
                        registrations.add(registration);
                    }
                }
            }
            RegistrationModelRepository repository = new RegistrationModelRepositoryImpl(registrations);
        }catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importServices(List<ServiceModel> services) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (isModel(line, ServiceModel.ENTITY_TOKEN)) {
                    ServiceModel service = converter.convertStringToService(line);
                    if (service.getId() == FacadeImpl.getInstance().getService(service.getId()).getId()) {
                        FacadeImpl.getInstance().updateService(service);
                    } else {
                        services.add(service);
                        servicesMap.put(service.getId(), service);
                    }
                }
            }
            ServiceModelRepository repository = new ServiceModelRepositoryImpl(services);
        }catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    //initialize maps for successfully update the object
    private void initMaps(List<ServiceModel> services, List<RoomModel> rooms) {
        servicesMap = new HashMap<>();
        roomsMap = new HashMap<>();
        if(services != null) {
            for (ServiceModel service : services) {
                servicesMap.put(service.getId(), service);
            }
        }
        if(rooms != null) {
            for (RoomModel room : rooms) {
                roomsMap.put(room.getId(), room);
            }
        }
    }

    private boolean isModel(String string, String token) {
        String[] values = string.split(";");
        return values[0].contains(token);
    }
}
