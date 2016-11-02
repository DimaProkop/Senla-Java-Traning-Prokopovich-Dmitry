package com.training.senla.model;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModel extends BaseModel implements Cloneable{
    private static final Logger LOG = LogManager.getLogger(GuestModel.class);

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

    public void addGuest(GuestModel guestModel) {
        if(guests == null) {
            guests = new ArrayList<>();
        }
        try {
            if(guests.size()<capacity){
                setStatus(guests.size()+1==capacity ? RoomStatus.BUSY : RoomStatus.FREE);
                guests.add(guestModel);
            }else {
                LOG.info("Is full");
            }
        } catch (Exception e) {
            LOG.error(e);
        }

    }

    public void removeGuest(GuestModel guestModel) {
        if(guests == null) {
            LOG.info("Guests is empty");
        }
        try {
            for (int i = 0; i < guests.size(); i++) {
                if (guests.get(i).getId() == guestModel.getId()) {
                    guests.remove(i);
                    setStatus(RoomStatus.FREE);
                    return;
                }
            }
        } catch (Exception e) {
            LOG.error(e);
            LOG.info("Guest not found");
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
