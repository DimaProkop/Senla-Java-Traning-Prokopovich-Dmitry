package com.training.senla.model;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModel extends BaseModel implements Cloneable, Serializable{
    private static final long serialVersionUID = 3950254982747535717L;

    private double price;

    private int capacity;

    private RoomStatus status;

    private RoomsSection section;

    private int rating;

    private List<GuestModel> guests;

    public RoomModel() {
    }

    public RoomModel(double price, int capacity, RoomsSection section, int rating) {
        this.price = price;
        this.capacity = capacity;
        this.status = RoomStatus.FREE;
        this.section = section;
        this.rating = rating;
        this.guests = new ArrayList<>();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public RoomsSection getSection() {
        return section;
    }

    public void setSection(RoomsSection section) {
        this.section = section;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<GuestModel> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestModel> guests) {
        this.guests = guests;
    }
}
