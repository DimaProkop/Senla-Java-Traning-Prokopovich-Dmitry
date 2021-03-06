package com.training.senla.util.initializer;

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
import com.training.senla.service.GuestModelService;
import com.training.senla.service.RegistrationModelService;
import com.training.senla.service.RoomModelService;
import com.training.senla.service.ServiceModelService;
import com.training.senla.service.impl.GuestModelServiceImpl;
import com.training.senla.service.impl.RegistrationModelServiceImpl;
import com.training.senla.service.impl.RoomModelServiceImpl;
import com.training.senla.service.impl.ServiceModelServiceImpl;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.impl.DataServiceImpl;
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

    private Converter converter;
    private DataService dataService;

    public Initializer() {
        this.dataService = new DataServiceImpl();
        this.converter = new ConverterImpl();
        this.fillDataObjects();
        this.fillServices();
    }

    private void fillServices() {
        GuestModelRepository guestModelRepository = new GuestModelRepositoryImpl(this.guests);
        RoomModelRepository roomModelRepository = new RoomModelRepositoryImpl(this.rooms);
        RegistrationModelRepository registrationModelRepository = new RegistrationModelRepositoryImpl(this.registrations);
        ServiceModelRepository serviceModelRepository = new ServiceModelRepositoryImpl(this.services);
        guestModelService = new GuestModelServiceImpl(guestModelRepository, registrationModelRepository);
        roomModelService = new RoomModelServiceImpl(roomModelRepository, guestModelRepository, registrationModelRepository);
        registrationModelService = new RegistrationModelServiceImpl(registrationModelRepository);
        serviceModelService = new ServiceModelServiceImpl(serviceModelRepository);
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
