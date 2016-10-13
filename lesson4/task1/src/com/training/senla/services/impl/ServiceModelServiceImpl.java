package com.training.senla.services.impl;

import com.training.senla.enums.ServicesSection;
import com.training.senla.models.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.services.ServiceModelService;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModelServiceImpl implements ServiceModelService {

    private ServiceModelRepository serviceModelRepository;

    @Override
    public void setService(ServiceModel serviceModel) {

    }

    @Override
    public ServiceModel getService(int id) {
        return null;
    }

    @Override
    public void update(ServiceModel serviceModel) {

    }

    @Override
    public void delete(ServiceModel serviceModel) {

    }

    @Override
    public List<ServiceModel> getAll() {
        return null;
    }

    @Override
    public List<ServiceModel> getSortedByPrice() {
        return null;
    }

    @Override
    public List<ServiceModel> getSortedByDate(Date date) {
        return null;
    }

    @Override
    public List<Integer> getPriceBySection(ServicesSection section) {
        return null;
    }
}
