package com.training.senla.repository;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceModelRepository {
    void setService(ServiceModel serviceModel);

    ServiceModel getService(int id);

    void update(ServiceModel serviceModel);

    void delete(ServiceModel serviceModel);

    List<ServiceModel> getAll();

    List<ServiceModel> getSortedByPrice();

    List<ServiceModel> getSortedByDate(LocalDate date);

    List<Double> getPriceBySection(ServicesSection section);
}
