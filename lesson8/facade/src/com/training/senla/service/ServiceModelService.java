package com.training.senla.service;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceModelService {
    void addService(ServiceModel serviceModel);

    ServiceModel getService(int id);

    void update(ServiceModel serviceModel);

    void delete(ServiceModel serviceModel);

    List<ServiceModel> getAll();

    List<ServiceModel> getSortedByPrice();

    List<ServiceModel> getSortedByDate(Date date);

    List<Double> getPriceBySection(ServicesSection section);

    void setServiceModelRepository(ServiceModelRepository serviceModelRepository);
}
