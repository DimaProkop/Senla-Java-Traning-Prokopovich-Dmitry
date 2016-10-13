package com.training.senla.models;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModel {
    private int roomId;
    private int price;
    private int capacity;
    private RoomStatus status;
    private RoomsSection section;
    private int rating;
    private Date startDate;
    private Date finalDate;
    private List<GuestModel> guests;

    public RoomModel() {
    }

    public RoomModel(int roomId, int price, int capacity, RoomStatus status, RoomsSection section, int rating, Date startDate, Date finalDate, List<GuestModel> guests) {
        this.roomId = roomId;
        this.price = price;
        this.capacity = capacity;
        this.status = status;
        this.section = section;
        this.rating = rating;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.guests = guests;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public List<GuestModel> getGuests() {
        return guests;
    }

    public void setGuests(List<GuestModel> guests) {
        this.guests = guests;
    }
}
