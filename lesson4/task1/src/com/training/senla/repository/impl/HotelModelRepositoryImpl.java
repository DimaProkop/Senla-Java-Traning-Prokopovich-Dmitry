package com.training.senla.repository.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.HotelModelRepository;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class HotelModelRepositoryImpl implements HotelModelRepository {

    private List<GuestModel> guestModels;
    private List<RoomModel> roomModels;
    private List<ServiceModel> serviceModels;

    public HotelModelRepositoryImpl(List<GuestModel> guestModels, List<RoomModel> roomModels, List<ServiceModel> serviceModels) {
        this.guestModels = guestModels;
        this.roomModels = roomModels;
        this.serviceModels = serviceModels;
    }

    @Override
    public List<GuestModel> getGuests() {
        return this.guestModels;
    }

    @Override
    public void setGuests(List<GuestModel> guests) {
        this.guestModels = guests;
    }

    @Override
    public List<RoomModel> getRooms() {
        return this.roomModels;
    }

    @Override
    public void setRooms(List<RoomModel> rooms) {
        this.roomModels = rooms;
    }

    @Override
    public List<ServiceModel> getServices() {
        return this.serviceModels;
    }

    @Override
    public void setServices(List<ServiceModel> services) {
        this.serviceModels = services;
    }
}
