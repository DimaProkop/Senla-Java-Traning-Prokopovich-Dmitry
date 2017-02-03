package com.training.senla.model;

import com.training.senla.annotation.CsvEntity;
import com.training.senla.annotation.CsvProperty;
import com.training.senla.annotation.CsvPropertyLink;
import com.training.senla.enums.PropertyType;
import com.training.senla.enums.ServicesSection;
import org.hibernate.annotations.Type;

import javax.enterprise.inject.Typed;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by prokop on 13.10.16.
 */
@Entity
@Table(name = "service")
@CsvEntity(filename = "service.csv", valuesSeparator = ";", entityId = "id")
public class Service extends BaseModel{
    private static final long serialVersionUID = 1069546016452590380L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2, escape = true)
    private String name;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3, escape = true)
    private double price;

    @Column
    @Enumerated(EnumType.STRING)
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1, escape = true)
    private ServicesSection section;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4, escape = true)
    private Date startDate;

    @Column
    @CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5, escape = true)
    private Date finalDate;

    @ManyToOne
    @JoinColumn(name = "guestId")
    @CsvPropertyLink(propertyType = PropertyType.CompositeProperty, keyField = "id")
    private Guest guest;

    public Service() {

    }

    public Service(String name, double price, ServicesSection section, Date startDate, Date finalDate) {
        this.name = name;
        this.price = price;
        this.section = section;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.guest = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ServicesSection getSection() {
        return section;
    }

    public void setSection(ServicesSection section) {
        this.section = section;
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
}
