package com.training.senla.service;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.repository.RoomModelRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface RoomModelService {
    void addRoom(RoomModel roomModel);

    RoomModel getRoom(int id);

    void update(RoomModel roomModel);

    void delete(RoomModel roomModel);

    void registerGuest(GuestModel guestModel, RoomModel roomModel, Date startDate, Date finalDate);

    void evictGuest(GuestModel guestModel);

    RoomModel cloneRoom(int id);

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

    void setRoomModelRepository(RoomModelRepository roomModelRepository);

    void setGuestModelRepository(GuestModelRepository guestModelRepository);

    void setRegistrationModelRepository(RegistrationModelRepository registrationModelRepository);
}
