package com.training.senla.util.initializer;

import com.training.senla.di.DependencyInjection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.repository.RoomModelRepository;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.service.GuestModelService;
import com.training.senla.service.RegistrationModelService;
import com.training.senla.service.RoomModelService;
import com.training.senla.service.ServiceModelService;
import com.training.senla.util.service.DataService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 15.10.16.
 */
public class Initializer {
    private static final Logger LOG = LogManager.getLogger(Initializer.class);

    private GuestModelService guestModelService;
    private RoomModelService roomModelService;
    private RegistrationModelService registrationModelService;
    private ServiceModelService serviceModelService;

    private List<GuestModel> guests = new ArrayList<>();
    private List<RoomModel> rooms = new ArrayList<>();
    private List<RegistrationModel> registrations = new ArrayList<>();
    private List<ServiceModel> services = new ArrayList<>();

    private DataService dataService;

    public Initializer() {
        this.dataService = (DataService) DependencyInjection.getInstance(DataService.class);
        this.fillDataObjects();
        this.fillServices();
    }

    private void fillServices() {
        GuestModelRepository guestModelRepository = (GuestModelRepository) DependencyInjection.getInstance(GuestModelRepository.class);
        guestModelRepository.setGuests(this.guests);
        guestModelRepository.calcCurrentId();
        RoomModelRepository roomModelRepository = (RoomModelRepository) DependencyInjection.getInstance(RoomModelRepository.class);
        roomModelRepository.setRooms(this.rooms);
        roomModelRepository.calcCurrentId();
        RegistrationModelRepository registrationModelRepository = (RegistrationModelRepository) DependencyInjection.getInstance(RegistrationModelRepository.class);
        registrationModelRepository.setRegistrations(this.registrations);
        registrationModelRepository.calcCurrentId();
        ServiceModelRepository serviceModelRepository = (ServiceModelRepository) DependencyInjection.getInstance(ServiceModelRepository.class);
        serviceModelRepository.setServices(this.services);
        serviceModelRepository.calcCurrentId();
        guestModelService = (GuestModelService) DependencyInjection.getInstance(GuestModelService.class);
        guestModelService.setGuestModelRepository(guestModelRepository);
        guestModelService.setRegistrationModelRepository(registrationModelRepository);

        roomModelService = (RoomModelService) DependencyInjection.getInstance(RoomModelService.class);
        roomModelService.setGuestModelRepository(guestModelRepository);
        roomModelService.setRoomModelRepository(roomModelRepository);
        roomModelService.setRegistrationModelRepository(registrationModelRepository);

        registrationModelService = (RegistrationModelService) DependencyInjection.getInstance(RegistrationModelService.class);
        registrationModelService.setRegistrationModelRepository(registrationModelRepository);

        serviceModelService = (ServiceModelService) DependencyInjection.getInstance(ServiceModelService.class);
        serviceModelService.setServiceModelRepository(serviceModelRepository);
    }

    private void fillDataObjects() {
        List<Object> data = dataService.loadData();
        convertDataToModel(data);
    }

    private void convertDataToModel(List<Object> data) {
        try {
            this.guests = (List<GuestModel>) data.get(0);
            this.rooms = (List<RoomModel>) data.get(1);
            this.services = (List<ServiceModel>) data.get(2);
            this.registrations = (List<RegistrationModel>) data.get(3);
        }catch (Exception e) {
            LOG.error(e);
        }
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
