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
import com.training.senla.util.io.importer.Importer;

import java.util.List;

/**
 * Created by prokop on 15.10.16.
 */
public class Initializer {

    private List<GuestModel> guestModels;
    private List<RoomModel> roomModels;
    private List<RegistrationModel> registrationModels;
    private List<ServiceModel> serviceModels;

    private GuestModelService guestModelService;
    private RoomModelService roomModelService;
    private RegistrationModelService registrationModelService;
    private ServiceModelService serviceModelService;

    private Importer importer;

    public Initializer(Importer importer) {
        this.importer = importer;
    }

    private int findMaxId(List<? extends BaseModel> list) {
        int maxId = 0;
        for (BaseModel value : list) {
            if(value.getId() > maxId) {
                maxId = value.getId();
            }
        }
        return maxId;
    }

    public void fillServices() {
        GuestModelRepositoryImpl.currentId = findMaxId(this.guestModels)+1;
        RoomModelRepositoryImpl.currentId = findMaxId(this.roomModels)+1;
        RegistrationModelRepositoryImpl.currentId = findMaxId(this.registrationModels)+1;
        ServiceModelRepositoryImpl.currentId = findMaxId(this.serviceModels)+1;
        GuestModelRepository guestModelRepository = new GuestModelRepositoryImpl(this.guestModels);
        RoomModelRepository roomModelRepository = new RoomModelRepositoryImpl(this.roomModels);
        RegistrationModelRepository registrationModelRepository = new RegistrationModelRepositoryImpl(this.registrationModels);
        ServiceModelRepository serviceModelRepository = new ServiceModelRepositoryImpl(this.serviceModels);
        guestModelService = new GuestModelServiceImpl(guestModelRepository, registrationModelRepository);
        roomModelService = new RoomModelServiceImpl(roomModelRepository, guestModelRepository, registrationModelRepository);
        registrationModelService = new RegistrationModelServiceImpl(registrationModelRepository);
        serviceModelService = new ServiceModelServiceImpl(serviceModelRepository);
    }

    public void fillDataObjects() {
        this.serviceModels = importer.importServices();
        this.roomModels = importer.importRooms();
        this.guestModels = importer.importGuests();
        this.registrationModels = importer.importRegistrations();
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
