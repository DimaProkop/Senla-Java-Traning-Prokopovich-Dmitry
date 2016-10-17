package com.training.senla.model;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModel {
    private int roomId;
    private double price;
    private int capacity;
    private RoomStatus status;
    private RoomsSection section;
    private int rating;
    private List<GuestModel> guests;

    public RoomModel() {
    }

    public RoomModel(double price, int capacity, RoomsSection section, int rating, List<GuestModel> guests) {
        this.price = price;
        this.capacity = capacity;
        this.status = RoomStatus.FREE;
        this.section = section;
        this.rating = rating;
        this.guests = guests;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
