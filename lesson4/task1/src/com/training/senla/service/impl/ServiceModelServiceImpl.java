package com.training.senla.service.impl;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.service.ServiceModelService;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModelServiceImpl implements ServiceModelService {

    private ServiceModelRepository serviceModelRepository;

    public ServiceModelServiceImpl(ServiceModelRepository serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }

    @Override
    public void setService(ServiceModel serviceModel) {
        serviceModelRepository.setService(serviceModel);
    }

    @Override
    public ServiceModel getService(int id) {
        return serviceModelRepository.getService(id);
    }

    @Override
    public void update(ServiceModel serviceModel) {
        serviceModelRepository.update(serviceModel);
    }

    @Override
    public void delete(ServiceModel serviceModel) {
        serviceModelRepository.delete(serviceModel);
    }

    @Override
    public void setAll(List<ServiceModel> serviceModels) {
        serviceModelRepository.setAll(serviceModels);
    }

    @Override
    public List<ServiceModel> getAll() {
        return serviceModelRepository.getAll();
    }

    @Override
    public List<ServiceModel> getSortedByPrice() {
        return serviceModelRepository.getSortedByPrice();
    }

    @Override
    public List<ServiceModel> getSortedByDate(LocalDate date) {
        return serviceModelRepository.getSortedByDate(date);
    }

    @Override
    public List<Double> getPriceBySection(ServicesSection section) {
        return serviceModelRepository.getPriceBySection(section);
    }
}
