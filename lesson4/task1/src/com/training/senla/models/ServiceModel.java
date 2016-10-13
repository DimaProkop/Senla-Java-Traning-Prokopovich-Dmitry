package com.training.senla.models;

import com.training.senla.enums.ServicesSection;

import java.util.Date;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModel {
    private int serviceId;
    private String name;
    private int price;
    private ServicesSection section;
    private Date startDate;
    private Date finalDate;

    public ServiceModel() {

    }

    public ServiceModel(int serviceId, String name, int price, ServicesSection section, Date startDate, Date finalDate) {
        this.serviceId = serviceId;
        this.name = name;
        this.price = price;
        this.section = section;
        this.startDate = startDate;
        this.finalDate = finalDate;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
