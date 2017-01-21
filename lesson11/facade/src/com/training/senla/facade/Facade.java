package com.training.senla.facade;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface Facade {
    void init();

    GuestModel getGuest(int id);

    void addGuest(GuestModel guest);

    void updateGuest(GuestModel guest);

    RoomModel getRoom(int id);

    void addRoom(RoomModel room);

    RegistrationModel getRegistration(int id);

    ServiceModel getService(int id);

    void updateService(ServiceModel service);

    void addService(ServiceModel service);

    void updateRegistration(RegistrationModel registration);

    void addRegistration(RegistrationModel registration);

    List<GuestModel> getAllGuests();

    List<RoomModel> getAllRooms();

    List<RoomModel> getSortedByPrice();

    List<RoomModel> getSortedByCapacity();

    List<RoomModel> getSortedByRating();

    List<RoomModel> getAllFreeRooms();

    List<GuestModel> getGuestsRoom();

    void updateRoom(RoomModel roomModel);

    int getCountFreeRooms();

    int getCountGuests();

    List<RoomModel> getReleasedRoomsInFuture(Date date);

    double getSumPaymentRoom(GuestModel guestModel, RoomModel roomModel);

    List<ServiceModel> getGuestServices(GuestModel guestModel);

    List<Double> getPricesService();

    List<ServiceModel> getAllServices();

    List<Double> getPricesRoom();

    void registerGuest(GuestModel guestModel, RoomModel roomModel, Date startDate, Date finalDate);

    void evictGuest(GuestModel guestModel);

    void changeServicePrice(ServiceModel serviceModel, double price);

    void changeRoomPrice(RoomModel roomModel, double price);

    boolean changeRoomStatus(RoomModel roomModel);

    void cloneRoom(int id);

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
