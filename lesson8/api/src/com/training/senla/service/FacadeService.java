package com.training.senla.service;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.List;

/**
 * Created by dmitry on 5.12.16.
 */
public interface FacadeService {
    com.training.senla.model.GuestModel getGuest(Object id);

    void addGuest(Object guest);

    void updateGuest(Object guest);

    RoomModel getRoom(Object id);

    void addRoom(Object room);

    RegistrationModel getRegistration(Object id);

    ServiceModel getService(Object id);

    void updateService(Object service);

    void addService(Object service);

    void updateRegistration(Object registration);

    void addRegistration(Object registration);

    List<GuestModel> getAllGuests();

    List<RoomModel> getAllRooms();

    List<RoomModel> getSortedByPrice();

    List<RoomModel> getSortedByCapacity();

    List<RoomModel> getSortedByRating();

    List<RoomModel> getAllFreeRooms();

    List<GuestModel> getGuestsRoom();

    void updateRoom(Object roomModel);

    int getCountFreeRooms();

    int getCountGuests();

    List<RoomModel> getReleasedRoomsInFuture(Object date);

    double getSumPaymentRoom(Object params);

    List<ServiceModel> getGuestServices(Object guestModel);

    List<Double> getPricesService();

    List<ServiceModel> getAllServices();

    List<Double> getPricesRoom();

    void registerGuest(Object params);

    void evictGuest(Object guestModel);

    void changeServicePrice(Object params);

    void changeRoomPrice(Object params);

    boolean changeRoomStatus(Object roomModel);

    void cloneRoom(Object id);

    List<RegistrationModel> getAllRegistrations();

    //imports
    void importGuests();

    void importRegistrations();

    void importRooms();

    void importServices();

    //exports
    void exportGuests();

    void exportRegistrations();

    void exportRooms();

    void exportServices();

    void exportAll();
}
