package com.training.senla.facade;

import com.danco.training.TextFileWorker;
import com.training.senla.enums.RoomStatus;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface Facade {
    void init(TextFileWorker textFileWorker);

    GuestModel getGuest(int id);

    void setGuest(GuestModel guest);

    RoomModel getRoom(int id);

    void setRoom(RoomModel room);

    RegistrationModel getRegistration(int id);

    ServiceModel getService(int id);

    void setService(ServiceModel service);

    List<GuestModel> getAllGuests();

    List<RoomModel> getAllRooms();

    List<RoomModel> getSortedByPrice();

    List<RoomModel> getSortedByCapacity();

    List<RoomModel> getSortedByRating();

    List<RoomModel> getAllFreeRooms();

    List<GuestModel> getGuestsRoom();

    int getCountFreeRooms();

    int getCountGuests();

    List<RoomModel> getReleasedRoomsInFuture(LocalDate date);

    double getSumPaymentRoom(GuestModel guestModel, RoomModel roomModel);

    List<ServiceModel> getGuestServices(GuestModel guestModel);

    List<Double> getPricesService();

    List<ServiceModel> getAllServices();

    List<Double> getPricesRoom();

    void registerGuest(GuestModel guestModel, RoomModel roomModel, LocalDate startDate, LocalDate finalDate);

    void evictGuest(GuestModel guestModel);

    void changeServicePrice(ServiceModel serviceModel, double price);

    void changeRoomPrice(RoomModel roomModel, double price);

    List<RegistrationModel> getAllRegistrations();

    //imports
    List<GuestModel> importGuests();

    List<RegistrationModel> importRegistrations();

    List<RoomModel> importRooms();

    List<ServiceModel> importServices();

    //exports
    void exportGuests(List<GuestModel> guests);

    void exportRegistrations(List<RegistrationModel> registrations);

    void exportRooms(List<RoomModel> rooms);

    void exportServices(List<ServiceModel> services);

    void exportAll();
}
