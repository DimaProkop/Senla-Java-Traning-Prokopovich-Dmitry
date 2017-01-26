package com.training.senla.facade;

import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.util.service.StreamService;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface Facade {
    void init();

    Guest getGuest(int id);

    void addGuest(Guest guest);

    void updateGuest(Guest guest);

    Room getRoom(int id);

    void addRoom(Room room);

    Registration getRegistration(int id);

    Service getService(int id);

    void updateService(Service service);

    void addService(Service service);

    void updateRegistration(Registration registration);

    void addRegistration(Registration registration);

    List<Guest> getAllGuests();

    List<Room> getAllRooms();

    List<Room> getSortedByPrice();

    List<Room> getSortedByCapacity();

    List<Room> getSortedByRating();

    List<Room> getAllFreeRooms();

    List<Guest> getGuestsRoom();

    void updateRoom(Room room);

    int getCountFreeRooms();

    int getCountGuests();

    List<Room> getReleasedRoomsInFuture(Date date);

    double getSumPaymentRoom(Guest guest, Room room);

    List<Service> getGuestServices(Guest guest);

    List<Double> getPricesService();

    List<Service> getAllServices();

    List<Double> getPricesRoom();

    void registerGuest(Guest guest, Room room, Date startDate, Date finalDate);

    void evictGuest(Guest guest);

    void changeServicePrice(Service service, double price);

    void changeRoomPrice(Room room, double price);

    boolean changeRoomStatus(Room room);

    void cloneRoom(int id);

    List<Registration> getAllRegistrations();

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

    StreamService getStreamService();
}
