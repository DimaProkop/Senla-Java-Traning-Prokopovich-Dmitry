package com.training.senla.service;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface GuestModelService {
    void setGuest(GuestModel guestModel);
    GuestModel getGuest(int id);
    void update(GuestModel guestModel);
    void delete(GuestModel guestModel);
    List<GuestModel> getAll();
    List<GuestModel> getSortedByFinalDate();
    List<GuestModel> getSortedByName();
    double getSumByRoom(RoomModel roomModel);
    int getCount();
}
