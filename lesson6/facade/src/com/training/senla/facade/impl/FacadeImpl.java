package com.training.senla.facade.impl;

import com.training.senla.ClassSetting;
import com.training.senla.enums.RoomStatus;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.service.GuestModelService;
import com.training.senla.service.RegistrationModelService;
import com.training.senla.service.RoomModelService;
import com.training.senla.service.ServiceModelService;
import com.training.senla.util.initializer.Initializer;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.io.exporter.impl.ExporterImpl;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.io.importer.impl.ImporterImpl;
import com.training.senla.util.service.StreamService;
import com.training.senla.util.service.impl.StreamServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by prokop on 13.10.16.
 */
public class FacadeImpl implements Facade{

    private GuestModelService guestModelService;
    private RoomModelService roomModelService;
    private RegistrationModelService registrationModelService;
    private ServiceModelService serviceModelService;

    private Initializer initializer;

    private Importer importer;
    private Exporter exporter;

    private StreamService streamService;
    private ClassSetting setting;

    private static Facade facade;

    public static Facade getInstance() {
        if(facade == null) {
            facade = new FacadeImpl();
        }
        return facade;
    }

    @Override
    public void init() {
        this.setting = new ClassSetting();
        this.streamService = new StreamServiceImpl();
        this.initializer = new Initializer();
        this.fillServicesFromInitializer();
        this.importer = new ImporterImpl(this.serviceModelService.getAll(), this.roomModelService.getAll());
        this.exporter = new ExporterImpl(streamService);
    }

    @Override
    public String getProperty(String value) {
        Map<String, String> props = this.setting.getPropsHolder();
        return props.get(value);
    }

    private void fillServicesFromInitializer() {
        this.guestModelService = this.initializer.getGuestModelService();
        this.roomModelService = this.initializer.getRoomModelService();
        this.registrationModelService = this.initializer.getRegistrationModelService();
        this.serviceModelService = this.initializer.getServiceModelService();
    }

    @Override
    public GuestModel getGuest(int id) {
        return guestModelService.getGuest(id);
    }

    @Override
    public void addGuest(GuestModel guest) {
        guestModelService.addGuest(guest);
    }

    @Override
    public void updateGuest(GuestModel guest) {
        guestModelService.update(guest);
    }

    @Override
    public RoomModel getRoom(int id) {
        return roomModelService.getRoom(id);
    }

    @Override
    public void addRoom(RoomModel room) {
        roomModelService.addRoom(room);
    }

    @Override
    public RegistrationModel getRegistration(int id) {
        return registrationModelService.getRegistration(id);
    }

    @Override
    public ServiceModel getService(int id) {
        return serviceModelService.getService(id);
    }

    @Override
    public void updateService(ServiceModel service) {
        serviceModelService.update(service);
    }

    @Override
    public void addService(ServiceModel service) {
        serviceModelService.addService(service);
    }

    @Override
    public void updateRegistration(RegistrationModel registration) {
        registrationModelService.update(registration);
    }

    @Override
    public List<GuestModel> getAllGuests() {
        return guestModelService.getAll();
    }

    @Override
    public List<RoomModel> getAllRooms() {
        return roomModelService.getAll();
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        return roomModelService.getSortedByPrice();
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        return roomModelService.getSortedByCapacity();
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        return roomModelService.getSortedByRating();
    }

    @Override
    public List<RoomModel> getAllFreeRooms() {
        return roomModelService.getAll(RoomStatus.FREE);
    }

    @Override
    public List<GuestModel> getGuestsRoom() {
        return guestModelService.getAll();
    }

    @Override
    public void updateRoom(RoomModel roomModel) {
        roomModelService.update(roomModel);
    }

    @Override
    public int getCountFreeRooms() {
        return roomModelService.getCountFreeRooms();
    }

    @Override
    public int getCountGuests() {
        return guestModelService.getCount();
    }

    @Override
    public List<RoomModel> getReleasedRoomsInFuture(Date date) {
        return roomModelService.getReleasedInFuture(date);
    }

    @Override
    public double getSumPaymentRoom(GuestModel guestModel, RoomModel roomModel) {
        return guestModelService.getSumByRoom(roomModel, guestModel);
    }

    @Override
    public List<ServiceModel> getGuestServices(GuestModel guestModel) {
        return guestModelService.getServicesByPrice(guestModel);
    }

    @Override
    public List<Double> getPricesService() {
        return null;
    }

    @Override
    public List<ServiceModel> getAllServices() {
        return serviceModelService.getAll();
    }

    @Override
    public List<Double> getPricesRoom() {
        return null;
    }

    @Override
    public void registerGuest(GuestModel guestModel, RoomModel roomModel, Date startDate, Date finalDate) {
        roomModelService.registerGuest(guestModel, roomModel, startDate, finalDate);
    }

    @Override
    public void evictGuest(GuestModel guestModel) {
        roomModelService.evictGuest(guestModel);
    }


    @Override
    public void changeServicePrice(ServiceModel serviceModel, double price) {
        serviceModel.setPrice(price);
        serviceModelService.update(serviceModel);
    }

    @Override
    public void changeRoomPrice(RoomModel roomModel, double price) {
        roomModel.setPrice(price);
        roomModelService.update(roomModel);
    }

    @Override
    public boolean changeRoomStatus(RoomModel roomModel) {
        boolean isBlock = Boolean.parseBoolean(this.getProperty("block.status"));
        if(isBlock) {
            roomModel.setStatus(RoomStatus.MAINTAINED);
            roomModelService.update(roomModel);
            return true;
        }
        return false;
    }

    @Override
    public RoomModel cloneRoom(int id) {
        return roomModelService.cloneRoom(id);
    }

    @Override
    public List<RegistrationModel> getAllRegistrations() {
        return registrationModelService.getAll();
    }

    @Override
    public void importGuests() {
        importer.importGuests(this.guestModelService.getAll());
    }

    @Override
    public void importRegistrations() {
        importer.importRegistrations(this.registrationModelService.getAll());
    }

    @Override
    public void importRooms() {
        importer.importRooms(this.roomModelService.getAll());
    }

    @Override
    public void importServices() {
        importer.importServices(this.serviceModelService.getAll());
    }

    @Override
    public void exportGuests() {
        exporter.exportGuests(this.guestModelService.getAll());
    }

    @Override
    public void exportRegistrations() {
        exporter.exportRegistrations(this.registrationModelService.getAll());
    }

    @Override
    public void exportRooms() {
        exporter.exportRooms(this.roomModelService.getAll());
    }

    @Override
    public void exportServices() {
        exporter.exportServices(this.serviceModelService.getAll());
    }

    @Override
    public void exportAll() {
        exporter.exportAll(this.getAllGuests(), this.getAllRegistrations(), this.getAllRooms(), this.getAllServices());
    }
}
