package com.training.senla.services;

import com.training.senla.models.GuestModel;
import com.training.senla.models.RoomModel;
import com.training.senla.models.ServiceModel;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface HotelModelService {
    List<GuestModel> getGuests();
    void setGuests(List<GuestModel> guests);
    List<RoomModel> getRooms();
    void setRooms(List<RoomModel> rooms);
    List<ServiceModel> getServices();
    void setServices(List<ServiceModel> services);
}
