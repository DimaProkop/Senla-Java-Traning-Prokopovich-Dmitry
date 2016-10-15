package com.training.senla.repository;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface HotelModelRepository {
    List<GuestModel> getGuests();
    void setGuests(List<GuestModel> guests);
    List<RoomModel> getRooms();
    void setRooms(List<RoomModel> rooms);
    List<ServiceModel> getServices();
    void setServices(List<ServiceModel> services);
}
