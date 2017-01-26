package com.training.senla.model;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.enums.PropertyType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prokop on 13.10.16.
 */
@CsvEntity(filename = "registration.csv", valuesSeparator = ";", entityId = "id")
public class Registration extends BaseModel{
    private static final long serialVersionUID = -7389885297471547150L;

    private Integer id;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1, escape = true)
    private int guestId;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3, escape = true)
    private int roomId;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4, escape = true)
    private Date startDate;

    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2, escape = true)
    private Date finalDate;

    public Registration() {
    }

    public Registration(int guestId, int roomId, Date startDate, Date finalDate) {
        this.guestId = guestId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
