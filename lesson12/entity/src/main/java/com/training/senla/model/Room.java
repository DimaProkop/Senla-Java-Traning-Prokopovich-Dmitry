package com.training.senla.model;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.annotation.CsvPropertyLink;
import com.training.senla.enums.PropertyType;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
@Entity
@Table(name = "room")
@CsvEntity(filename = "room.csv", valuesSeparator = ";", entityId = "id")
public class Room extends BaseModel implements Cloneable{
    private static final long serialVersionUID = 3950254982747535717L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5, escape = true)
    private Double price;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3, escape = false)
    private Integer capacity;

    @Column
    @Enumerated(EnumType.STRING)
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2, escape = true)
    private RoomStatus status;

    @Column
    @Enumerated(EnumType.STRING)
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1, escape = false)
    private RoomsSection section;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4, escape = true)
    private Integer rating;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @CsvPropertyLink(propertyType = PropertyType.CompositeProperty, keyField = "id")
    private List<Guest> guests;

    public Room() {
    }

    public Room(Double price, Integer capacity, RoomsSection section, Integer rating) {
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

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
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

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
