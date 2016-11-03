package com.training.senla.util.initializer;

import com.training.senla.model.*;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.repository.RoomModelRepository;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.repository.impl.GuestModelRepositoryImpl;
import com.training.senla.repository.impl.RegistrationModelRepositoryImpl;
import com.training.senla.repository.impl.RoomModelRepositoryImpl;
import com.training.senla.repository.impl.ServiceModelRepositoryImpl;
import com.training.senla.service.GuestModelService;
import com.training.senla.service.RegistrationModelService;
import com.training.senla.service.RoomModelService;
import com.training.senla.service.ServiceModelService;
import com.training.senla.service.impl.GuestModelServiceImpl;
import com.training.senla.service.impl.RegistrationModelServiceImpl;
import com.training.senla.service.impl.RoomModelServiceImpl;
import com.training.senla.service.impl.ServiceModelServiceImpl;
import com.training.senla.storage.Storage;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.impl.DataServiceImpl;

import java.util.List;

/**
 * Created by prokop on 15.10.16.
 */
public class Initializer {
    private GuestModelService guestModelService;
    private RoomModelService roomModelService;
    private RegistrationModelService registrationModelService;
    private ServiceModelService serviceModelService;

    private Converter converter;
    private DataService dataService;

    public Initializer() {
        this.dataService = new DataServiceImpl();
        this.converter = new ConverterImpl();
        this.fillDataObjects();
        this.fillServices();
    }

    private void fillServices() {
        GuestModelRepository guestModelRepository = new GuestModelRepositoryImpl(Storage.guests);
        RoomModelRepository roomModelRepository = new RoomModelRepositoryImpl(Storage.rooms);
        RegistrationModelRepository registrationModelRepository = new RegistrationModelRepositoryImpl(Storage.registrations);
        ServiceModelRepository serviceModelRepository = new ServiceModelRepositoryImpl(Storage.services);
        guestModelService = new GuestModelServiceImpl(guestModelRepository, registrationModelRepository);
        roomModelService = new RoomModelServiceImpl(roomModelRepository, guestModelRepository, registrationModelRepository);
        registrationModelService = new RegistrationModelServiceImpl(registrationModelRepository);
        serviceModelService = new ServiceModelServiceImpl(serviceModelRepository);
    }

    private void fillDataObjects() {
        List<Object> data = dataService.loadData();
        converter.convertDataToModel(data);
    }

    public GuestModelService getGuestModelService() {
        return this.guestModelService;
    }

    public RoomModelService getRoomModelService() {
        return this.roomModelService;
    }

    public RegistrationModelService getRegistrationModelService() {
        return this.registrationModelService;
    }

    public ServiceModelService getServiceModelService() {
        return this.serviceModelService;
    }
}
