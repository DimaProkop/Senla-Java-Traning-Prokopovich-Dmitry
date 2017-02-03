package com.training.senla.model;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.annotation.CsvPropertyLink;
import com.training.senla.enums.PropertyType;

import javax.persistence.*;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
@Entity
@Table(name = "guest")
@CsvEntity(filename = "guest.csv", valuesSeparator = ";", entityId = "id")
public class Guest extends BaseModel{

    private static final long serialVersionUID = -4477116269261501412L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1, escape = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "roomId")
    @CsvPropertyLink(propertyType = PropertyType.CompositeProperty, keyField = "id")
    private Room room;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    @CsvPropertyLink(propertyType = PropertyType.CompositeProperty, keyField = "id")
    private List<Service> serviceList;

    public Guest() {

    }

    public Guest(String name) {
        this.name = name;
        this.room = null;
        this.serviceList = null;
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

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
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
