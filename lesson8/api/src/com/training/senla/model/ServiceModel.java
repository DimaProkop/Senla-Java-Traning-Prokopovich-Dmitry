package com.training.senla.model;

import com.training.senla.enums.ServicesSection;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModel extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1069546016452590380L;

    private String name;

    private double price;

    private ServicesSection section;

    private Date startDate;

    private Date finalDate;

    public ServiceModel() {

    }

    public ServiceModel(String name, double price, ServicesSection section, Date startDate, Date finalDate) {
        this.name = name;
        this.price = price;
        this.section = section;
        this.startDate = startDate;
        this.finalDate = finalDate;
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
}
