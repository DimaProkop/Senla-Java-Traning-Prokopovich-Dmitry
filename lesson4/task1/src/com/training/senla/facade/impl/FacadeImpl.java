package com.training.senla.facade.impl;

import com.danco.training.TextFileWorker;
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

import java.time.LocalDate;
import java.util.List;

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

    @Override
    public void init(TextFileWorker textFileWorker) {
        this.importer = new ImporterImpl();
        this.importer.loadData(textFileWorker);
        this.exporter = new ExporterImpl(textFileWorker);
        this.initializer = new Initializer(importer);
        this.initializer.fillDataObjects();
        this.initializer.fillServices();
        this.fillServicesFromInitializer();
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
    public void setGuest(GuestModel guest) {
        guestModelService.setGuest(guest);
    }

    @Override
    public RoomModel getRoom(int id) {
        return roomModelService.getRoom(id);
    }

    @Override
    public void setRoom(RoomModel room) {
        roomModelService.setRoom(room);
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
    public void setService(ServiceModel service) {
        serviceModelService.setService(service);
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
    public List<RoomModel> getAllFreeRooms() {
        return roomModelService.getAll(RoomStatus.FREE);
    }

    @Override
    public List<GuestModel> getGuestsRoom() {
        return guestModelService.getAll();
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
    public List<RoomModel> getReleasedRoomsInFuture(LocalDate date) {
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
    public void registerGuest(GuestModel guestModel, RoomModel roomModel, LocalDate startDate, LocalDate finalDate) {
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
    public List<RegistrationModel> getAllRegistrations() {
        return registrationModelService.getAll();
    }

    @Override
    public List<GuestModel> importGuests() {
        if(this.importer == null) {
            this.importer = new ImporterImpl();
        }
        return importer.importGuests();
    }

    @Override
    public List<RegistrationModel> importRegistrations() {
        if(this.importer == null) {
            this.importer = new ImporterImpl();
        }
        return importer.importRegistrations();
    }

    @Override
    public List<RoomModel> importRooms() {
        if(this.importer == null) {
            this.importer = new ImporterImpl();
        }
        return importer.importRooms();
    }

    @Override
    public List<ServiceModel> importServices() {
        if(this.importer == null) {
            this.importer = new ImporterImpl();
        }
        return importer.importServices();
    }

    @Override
    public void exportGuests(List<GuestModel> guests) {
        exporter.exportGuests(this.guestModelService.getAll());
    }

    @Override
    public void exportRegistrations(List<RegistrationModel> registrations) {
        exporter.exportRegistrations(this.registrationModelService.getAll());
    }

    @Override
    public void exportRooms(List<RoomModel> rooms) {
        exporter.exportRooms(this.roomModelService.getAll());
    }

    @Override
    public void exportServices(List<ServiceModel> services) {
        exporter.exportServices(this.serviceModelService.getAll());
    }

    @Override
    public void exportAll() {
        exporter.exportAll(this.getAllServices(), this.getAllRooms(), this.getAllGuests(), this.getAllRegistrations());
    }
}
