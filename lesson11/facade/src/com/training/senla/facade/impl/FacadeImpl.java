package com.training.senla.facade.impl;

import com.training.senla.ClassSetting;
import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.RoomStatus;
import com.training.senla.facade.Facade;
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
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.io.exporter.impl.ExporterImpl;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.service.StreamService;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class FacadeImpl implements Facade {

    private GuestModelService guestModelService;
    private RoomModelService roomModelService;
    private RegistrationModelService registrationModelService;
    private ServiceModelService serviceModelService;

    private Importer importer;
    private Exporter exporter;

    private StreamService streamService;

    @Override
    public void init() {
        fillServices();
        streamService = (StreamService) DependencyInjection.getInstance(StreamService.class);
        importer = (Importer) DependencyInjection.getInstance(Importer.class);
        exporter = new ExporterImpl();
    }

    private void fillServices() {
        GuestModelRepository guestModelRepository = (GuestModelRepository) DependencyInjection.getInstance(GuestModelRepository.class);
        RoomModelRepository roomModelRepository = (RoomModelRepository) DependencyInjection.getInstance(RoomModelRepository.class);
        RegistrationModelRepository registrationModelRepository = (RegistrationModelRepository) DependencyInjection.getInstance(RegistrationModelRepository.class);
        ServiceModelRepository serviceModelRepository = (ServiceModelRepository) DependencyInjection.getInstance(ServiceModelRepository.class);
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

    @Override
    public GuestModel getGuest(int id) {
        GuestModel guest = null;
        synchronized (guestModelService) {
            guest = guestModelService.getGuest(id);
        }
        return guest;
    }

    @Override
    public void addGuest(GuestModel guest) {
        synchronized (guestModelService) {
            guestModelService.addGuest(guest);
        }
    }

    @Override
    public void updateGuest(GuestModel guest) {
        synchronized (guestModelService) {
            guestModelService.update(guest);
        }
    }

    @Override
    public RoomModel getRoom(int id) {
        RoomModel room = null;
        synchronized (roomModelService) {
            room = roomModelService.getRoom(id);
        }
        return room;
    }

    @Override
    public void addRoom(RoomModel room) {
        synchronized (roomModelService) {
            roomModelService.addRoom(room);
        }
    }

    @Override
    public RegistrationModel getRegistration(int id) {
        RegistrationModel registration = null;
        synchronized (registrationModelService) {
            registration = registrationModelService.getRegistration(id);
        }
        return registration;
    }

    @Override
    public ServiceModel getService(int id) {
        ServiceModel service = null;
        synchronized (serviceModelService) {
            service = serviceModelService.getService(id);
        }
        return service;
    }

    @Override
    public void updateService(ServiceModel service) {
        synchronized (serviceModelService) {
            serviceModelService.update(service);
        }
    }

    @Override
    public void addService(ServiceModel service) {
        synchronized (serviceModelService) {
            serviceModelService.addService(service);
        }
    }

    @Override
    public void updateRegistration(RegistrationModel registration) {
        synchronized (registrationModelService) {
            registrationModelService.update(registration);
        }
    }

    @Override
    public void addRegistration(RegistrationModel registration) {
        synchronized (registrationModelService) {
            registrationModelService.addRecord(registration);
        }
    }

    @Override
    public List<GuestModel> getAllGuests() {
        List<GuestModel> guests = null;
        synchronized (guestModelService) {
            guests = guestModelService.getAll();
        }
        return guests;
    }

    @Override
    public List<RoomModel> getAllRooms() {
        List<RoomModel> rooms = null;
        synchronized (roomModelService) {
            rooms = roomModelService.getAll();
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        List<RoomModel> rooms = null;
        synchronized (roomModelService) {
            rooms = roomModelService.getSortedByPrice();
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        List<RoomModel> rooms = null;
        synchronized (roomModelService) {
            rooms = roomModelService.getSortedByCapacity();
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        List<RoomModel> rooms = null;
        synchronized (roomModelService) {
            rooms = roomModelService.getSortedByRating();
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getAllFreeRooms() {
        List<RoomModel> rooms = null;
        synchronized (roomModelService) {
            rooms = roomModelService.getAll(RoomStatus.FREE);
        }
        return rooms;
    }

    @Override
    public List<GuestModel> getGuestsRoom() {
        List<GuestModel> guests = null;
        synchronized (guestModelService) {
            guests = guestModelService.getAll();
        }
        return guests;
    }

    @Override
    public void updateRoom(RoomModel roomModel) {
        synchronized (roomModelService) {
            roomModelService.update(roomModel);
        }
    }

    @Override
    public int getCountFreeRooms() {
        int count = 0;
        synchronized (roomModelService) {
            count = roomModelService.getCountFreeRooms();
        }
        return count;
    }

    @Override
    public int getCountGuests() {
        int count = 0;
        synchronized (guestModelService) {
            count = guestModelService.getCount();
        }
        return count;
    }

    @Override
    public List<RoomModel> getReleasedRoomsInFuture(Date date) {
        List<RoomModel> rooms = null;
        synchronized (roomModelService) {
            rooms = roomModelService.getReleasedInFuture(date);
        }
        return rooms;
    }

    @Override
    public double getSumPaymentRoom(GuestModel guestModel, RoomModel roomModel) {
        double sum = 0;
        synchronized (guestModelService) {
            sum = guestModelService.getSumByRoom(roomModel, guestModel);
        }
        return sum;
    }

    @Override
    public List<ServiceModel> getGuestServices(GuestModel guestModel) {
        List<ServiceModel> services = null;
        synchronized (guestModelService) {
            services = guestModelService.getServicesByPrice(guestModel);
        }
        return services;
    }

    @Override
    public List<Double> getPricesService() {
        return null;
    }

    @Override
    public List<ServiceModel> getAllServices() {
        List<ServiceModel> services = null;
        synchronized (serviceModelService) {
            services = serviceModelService.getAll();
        }
        return services;
    }

    @Override
    public List<Double> getPricesRoom() {
        return null;
    }

    @Override
    public void registerGuest(GuestModel guestModel, RoomModel roomModel, Date startDate, Date finalDate) {
        synchronized (roomModelService) {
            roomModelService.registerGuest(guestModel, roomModel, startDate, finalDate);
        }
    }

    @Override
    public void evictGuest(GuestModel guestModel) {
        synchronized (roomModelService) {
            roomModelService.evictGuest(guestModel);
        }
    }


    @Override
    public void changeServicePrice(ServiceModel serviceModel, double price) {
        serviceModel.setPrice(price);
        synchronized (serviceModelService) {
            serviceModelService.update(serviceModel);
        }
    }

    @Override
    public void changeRoomPrice(RoomModel roomModel, double price) {
        roomModel.setPrice(price);
        synchronized (roomModelService) {
            roomModelService.update(roomModel);
        }
    }

    @Override
    public boolean changeRoomStatus(RoomModel roomModel) {
        if (ClassSetting.getProps().isBlockStatus()) {
            roomModel.setStatus(RoomStatus.MAINTAINED);
            synchronized (roomModelService) {
                roomModelService.update(roomModel);
            }
            return true;
        }
        return false;
    }

    @Override
    public void cloneRoom(int id) {
        RoomModel room = null;
        synchronized (roomModelService) {
            room = roomModelService.cloneRoom(id);
            roomModelService.addRoom(room);
        }
    }

    @Override
    public List<RegistrationModel> getAllRegistrations() {
        List<RegistrationModel> registrations = null;
        synchronized (registrationModelService) {
            registrations = registrationModelService.getAll();
        }
        return registrations;
    }

    @Override
    public void importGuests() {
        synchronized (guestModelService) {
            importer.importGuests(this.guestModelService.getAll());
        }
    }

    @Override
    public void importRegistrations() {
        synchronized (registrationModelService) {
            importer.importRegistrations(this.registrationModelService.getAll());
        }
    }

    @Override
    public void importRooms() {
        synchronized (roomModelService) {
            importer.importRooms(this.roomModelService.getAll());
        }
    }

    @Override
    public void importServices() {
        synchronized (serviceModelService) {
            importer.importServices(this.serviceModelService.getAll());
        }
    }

    @Override
    public void exportGuests() {
        synchronized (guestModelService) {
            exporter.exportCollection(this.guestModelService.getAll(), GuestModel.class);
        }
    }

    @Override
    public void exportRegistrations() {
        synchronized (registrationModelService) {
            exporter.exportCollection(this.registrationModelService.getAll(), RegistrationModel.class);
        }
    }

    @Override
    public void exportRooms() {
        synchronized (roomModelService) {
            exporter.exportCollection(this.roomModelService.getAll(), RoomModel.class);
        }
    }

    @Override
    public void exportServices() {
        synchronized (serviceModelService) {
            exporter.exportCollection(this.serviceModelService.getAll(), ServiceModel.class);
        }
    }

    @Override
    public void exportAll() {
        exporter.exportAll(this.getAllGuests(), this.getAllRegistrations(), this.getAllRooms(), this.getAllServices());
    }

    public StreamService getStreamService() {
        return streamService;
    }
}
