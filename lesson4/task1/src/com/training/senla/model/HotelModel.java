package com.training.senla.model;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class HotelModel {
    private List<RoomModel> rooms;
    private List<ServiceModel> services;
    private List<GuestModel> guests;

    public HotelModel() {
    }

    public HotelModel(List<RoomModel> rooms, List<ServiceModel> services, List<GuestModel> guests) {
        this.rooms = rooms;
        this.services = services;
        this.guests = guests;
    }

    public List<GuestModel> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestModel> guests) {
        this.guests = guests;
    }

    public List<RoomModel> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }
}
