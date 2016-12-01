package com.training.senla.model;

import java.util.List;

/**
 * Created by dmitry on 30.11.16.
 */
public class Guest extends Base{
    private String name;
    private Room room;
    private List<Service> services;

    public Guest() {
    }

    public Guest(String name) {
        this.name = name;
        this.room = null;
        this.services = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
