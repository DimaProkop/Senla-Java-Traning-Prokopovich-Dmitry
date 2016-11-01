package com.training.senla.service;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface RoomModelService {
    void setRoom(RoomModel roomModel);

    RoomModel getRoom(int id);

    void update(RoomModel roomModel);

    void delete(RoomModel roomModel);

    void registerGuest(GuestModel guestModel, RoomModel roomModel, Date startDate, Date finalDate);

    void evictGuest(GuestModel guestModel);

    List<RoomModel> getAll();

    List<RoomModel> getSortedByPrice();

    List<RoomModel> getSortedByCapacity();

    List<RoomModel> getSortedByRating();

    //for free rooms
    List<RoomModel> getAll(RoomStatus status);

    List<RoomModel> getSortedByPrice(RoomStatus status);

    List<RoomModel> getSortedByCapacity(RoomStatus status);

    List<RoomModel> getSortedByRating(RoomStatus status);

    int getCountFreeRooms();

    List<RoomModel> getReleasedInFuture(Date date);

    List<RoomModel> getLatestGuests(int count);

    List<Double> getPriceBySection(RoomsSection section);
}
