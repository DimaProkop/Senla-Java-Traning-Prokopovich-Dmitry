package com.training.senla.model;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.annotation.CsvPropertyLink;
import com.training.senla.enums.PropertyType;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
@CsvEntity(filename = "room.csv", valuesSeparator = ";", entityId = "id")
public class RoomModel extends BaseModel implements Cloneable, Serializable{
    private static final Logger LOG = LogManager.getLogger(GuestModel.class);
    private static final long serialVersionUID = 3950254982747535717L;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5, escape = true)
    private double price;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3, escape = false)
    private int capacity;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2, escape = true)
    private RoomStatus status;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1, escape = false)
    private RoomsSection section;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4, escape = true)
    private int rating;

    @CsvPropertyLink(propertyType = PropertyType.CompositeProperty, keyField = "id")
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
        if(guests.contains(guestModel)) {
            LOG.info("Guest already settled");
        }else {
            try {
                if (guests.size() < capacity) {
                    setStatus(guests.size() + 1 == capacity ? RoomStatus.BUSY : RoomStatus.FREE);
                    guests.add(guestModel);
                } else {
                    LOG.info("Is full");
                }
            } catch (Exception e) {
                LOG.error(e);
            }
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
