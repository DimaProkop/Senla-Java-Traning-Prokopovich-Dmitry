package com.training.senla.model;

import com.training.senla.enums.ServicesSection;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModel extends BaseModel{
    private String name;
    private double price;
    private ServicesSection section;
    private LocalDate startDate;
    private LocalDate finalDate;

    public ServiceModel() {

    }

    public ServiceModel(String name, double price, ServicesSection section, LocalDate startDate, LocalDate finalDate) {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }
}
