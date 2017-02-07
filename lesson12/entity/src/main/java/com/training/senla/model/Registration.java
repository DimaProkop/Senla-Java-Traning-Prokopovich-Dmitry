package com.training.senla.model;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.enums.PropertyType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by prokop on 13.10.16.
 */
@Entity
@Table(name = "registration")
@CsvEntity(filename = "registration.csv", valuesSeparator = ";", entityId = "id")
public class Registration extends BaseModel{
    private static final long serialVersionUID = -7389885297471547150L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1, escape = true)
    private Guest guest;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3, escape = true)
    private Room room;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4, escape = true)
    private Date startDate;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2, escape = true)
    private Date finalDate;

    public Registration() {
    }

    public Registration(Guest guest, Room room, Date startDate, Date finalDate) {
        this.guest = guest;
        this.room = room;
        this.startDate = startDate;
        this.finalDate = finalDate;
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

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
