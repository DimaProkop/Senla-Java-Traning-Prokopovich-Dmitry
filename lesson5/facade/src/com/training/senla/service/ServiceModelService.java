package com.training.senla.service;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceModelService {
    void setService(ServiceModel serviceModel);

    ServiceModel getService(int id);

    void update(ServiceModel serviceModel);

    void delete(ServiceModel serviceModel);

    List<ServiceModel> getAll();

    List<ServiceModel> getSortedByPrice();

    List<ServiceModel> getSortedByDate(LocalDate date);

    List<Double> getPriceBySection(ServicesSection section);
}
