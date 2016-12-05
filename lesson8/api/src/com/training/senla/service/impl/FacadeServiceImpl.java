package com.training.senla.service.impl;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;
import com.training.senla.service.FacadeService;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.Date;
import java.util.List;

/**
 * Created by dmitry on 5.12.16.
 */
public class FacadeServiceImpl implements FacadeService {

    private static Facade facade;

    public FacadeServiceImpl() {

    }

    public static Facade getInstance() {
        if (facade == null) {
            facade = (Facade) DependencyInjection.getInstance(Facade.class);
            facade.init();
        }
        return facade;
    }

    @Override
    public GuestModel getGuest(Object id) {
        int currentId = (int) id;
        return FacadeServiceImpl.getInstance().getGuest(currentId);
    }

    @Override
    public void addGuest(Object guest) {
        GuestModel currentGuest = (GuestModel) guest;
        FacadeServiceImpl.getInstance().addGuest(currentGuest);
    }

    @Override
    public void updateGuest(Object guest) {
        GuestModel currentGuest = (GuestModel) guest;
        FacadeServiceImpl.getInstance().updateGuest(currentGuest);
    }

    @Override
    public RoomModel getRoom(Object id) {
        int currentId = (int) id;
        return FacadeServiceImpl.getInstance().getRoom(currentId);
    }

    @Override
    public void addRoom(Object room) {
        RoomModel currentRoom = (RoomModel) room;
        FacadeServiceImpl.getInstance().addRoom(currentRoom);
    }

    @Override
    public RegistrationModel getRegistration(Object id) {
        int currentId = (int) id;
        return FacadeServiceImpl.getInstance().getRegistration(currentId);
    }

    @Override
    public ServiceModel getService(Object id) {
        int currentId = (int) id;
        return FacadeServiceImpl.getInstance().getService(currentId);
    }

    @Override
    public void updateService(Object service) {
        ServiceModel currentService = (ServiceModel) service;
        FacadeServiceImpl.getInstance().updateService(currentService);
    }

    @Override
    public void addService(Object service) {
        ServiceModel currentService = (ServiceModel) service;
        FacadeServiceImpl.getInstance().addService(currentService);
    }

    @Override
    public void updateRegistration(Object registration) {
        RegistrationModel currentRecord = (RegistrationModel) registration;
        FacadeServiceImpl.getInstance().updateRegistration(currentRecord);
    }

    @Override
    public void addRegistration(Object registration) {
        RegistrationModel currentRecord = (RegistrationModel) registration;
        FacadeServiceImpl.getInstance().addRegistration(currentRecord);
    }

    @Override
    public List<GuestModel> getAllGuests() {
        return FacadeServiceImpl.getInstance().getAllGuests();
    }

    @Override
    public List<RoomModel> getAllRooms() {
        return FacadeServiceImpl.getInstance().getAllRooms();
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        return FacadeServiceImpl.getInstance().getSortedByPrice();
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        return FacadeServiceImpl.getInstance().getSortedByCapacity();
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        return FacadeServiceImpl.getInstance().getSortedByRating();
    }

    @Override
    public List<RoomModel> getAllFreeRooms() {
        return FacadeServiceImpl.getInstance().getAllFreeRooms();
    }

    @Override
    public List<GuestModel> getGuestsRoom() {
        return FacadeServiceImpl.getInstance().getGuestsRoom();
    }

    @Override
    public void updateRoom(Object roomModel) {
        RoomModel currentRoom = (RoomModel) roomModel;
        FacadeServiceImpl.getInstance().updateRoom(currentRoom);
    }

    @Override
    public int getCountFreeRooms() {
        return FacadeServiceImpl.getInstance().getCountFreeRooms();
    }

    @Override
    public int getCountGuests() {
        return FacadeServiceImpl.getInstance().getCountGuests();
    }

    @Override
    public List<RoomModel> getReleasedRoomsInFuture(Object date) {
        Date currentDate = (Date) date;
        return FacadeServiceImpl.getInstance().getReleasedRoomsInFuture(currentDate);
    }

    @Override
    public double getSumPaymentRoom(Object params) {
        List<Object> objects = (List<Object>) params;
        GuestModel guest = (GuestModel) objects.get(0);
        RoomModel room = (RoomModel) objects.get(1);
        return FacadeServiceImpl.getInstance().getSumPaymentRoom(guest, room);
    }

    @Override
    public List<ServiceModel> getGuestServices(Object guestModel) {
        GuestModel currentGuest = (GuestModel) guestModel;
        return FacadeServiceImpl.getInstance().getGuestServices(currentGuest);
    }

    @Override
    public List<Double> getPricesService() {
        return FacadeServiceImpl.getInstance().getPricesService();
    }

    @Override
    public List<ServiceModel> getAllServices() {
        return FacadeServiceImpl.getInstance().getAllServices();
    }

    @Override
    public List<Double> getPricesRoom() {
        return FacadeServiceImpl.getInstance().getPricesRoom();
    }

    @Override
    public void registerGuest(Object params) {
        List<Object> objects = (List<Object>) params;
        GuestModel guest = (GuestModel) objects.get(0);
        RoomModel room = (RoomModel) objects.get(1);
        Date statDate = (Date) objects.get(2);
        Date finalDate = (Date) objects.get(3);

        FacadeServiceImpl.getInstance().registerGuest(guest, room, statDate, finalDate);
    }

    @Override
    public void evictGuest(Object guestModel) {
        GuestModel currentGuest = (GuestModel) guestModel;
        FacadeServiceImpl.getInstance().evictGuest(currentGuest);
    }

    @Override
    public void changeServicePrice(Object params) {
        List<Object> objects = (List<Object>) params;
        ServiceModel service = (ServiceModel) objects.get(0);
        double value = (double) objects.get(1);

        FacadeServiceImpl.getInstance().changeServicePrice(service, value);
    }

    @Override
    public void changeRoomPrice(Object params) {
        List<Object> objects = (List<Object>) params;
        RoomModel room = (RoomModel) objects.get(0);
        double value = (double) objects.get(1);

        FacadeServiceImpl.getInstance().changeRoomPrice(room, value);
    }

    @Override
    public boolean changeRoomStatus(Object roomModel) {
        RoomModel room = (RoomModel) roomModel;
        return FacadeServiceImpl.getInstance().changeRoomStatus(room);
    }

    @Override
    public void cloneRoom(Object id) {
        int currentId = (int) id;
        FacadeServiceImpl.getInstance().cloneRoom(currentId);
    }

    @Override
    public List<RegistrationModel> getAllRegistrations() {
        return FacadeServiceImpl.getInstance().getAllRegistrations();
    }

    @Override
    public void importGuests() {
        FacadeServiceImpl.getInstance().importGuests();
    }

    @Override
    public void importRegistrations() {
        FacadeServiceImpl.getInstance().importRegistrations();
    }

    @Override
    public void importRooms() {
        FacadeServiceImpl.getInstance().importRooms();
    }

    @Override
    public void importServices() {
        FacadeServiceImpl.getInstance().importServices();
    }

    @Override
    public void exportGuests() {
        FacadeServiceImpl.getInstance().exportGuests();
    }

    @Override
    public void exportRegistrations() {
        FacadeServiceImpl.getInstance().exportRegistrations();
    }

    @Override
    public void exportRooms() {
        FacadeServiceImpl.getInstance().exportRooms();
    }

    @Override
    public void exportServices() {
        FacadeServiceImpl.getInstance().exportServices();
    }

    @Override
    public void exportAll() {
        FacadeServiceImpl.getInstance().exportAll();
    }
}
