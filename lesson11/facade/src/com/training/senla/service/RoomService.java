package com.training.senla.service;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.dao.GuestDao;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.dao.RoomDao;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface RoomService {
    void addRoom(Room room);

    Room getRoom(int id);

    void update(Room room);

    void delete(Room room);

    void registerGuest(Guest guest, Room room, Date startDate, Date finalDate);

    void evictGuest(Guest guest);

    Room cloneRoom(int id);

    List<Room> getAll(SortType type);

    //for free rooms
    List<Room> getAllFree(SortType type);

    int getCountFreeRooms();

    List<Room> getReleasedInFuture(Date date);

    List<Room> getLatestGuests(int count);

    List<Double> getPriceBySection(RoomsSection section);

    void setRoomDao(RoomDao roomDao);

    void setGuestDao(GuestDao guestDao);

    void setRegistrationDao(RegistrationDao registrationDao);
}
