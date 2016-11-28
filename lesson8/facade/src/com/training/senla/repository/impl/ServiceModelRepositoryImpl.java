package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModelRepositoryImpl implements ServiceModelRepository {

    private List<ServiceModel> services;
    private int currentId=1;

    public void calcCurrentId() {
        int maxId = 0;
        if(services == null) {
            currentId = 1;
        }else {
            for (ServiceModel service : services) {
                if (service.getId() > maxId) {
                    maxId = service.getId();
                }
            }
            currentId = maxId + 1;
        }
    }

    public ServiceModelRepositoryImpl() {
        calcCurrentId();
    }

    private int getServiceIndexById(int id) {
        for (int i = 0; i < services.size(); i++) {
            if(services.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setService(ServiceModel serviceModel) {
        serviceModel.setId(currentId++);
        services.add(serviceModel);
    }

    @Override
    public ServiceModel getService(int id) {
        ServiceModel service = null;
        if(id != -1) {
            service = services.get(id);
        }
        return service;
    }

    @Override
    public void update(ServiceModel serviceModel) {
        services.set(getServiceIndexById(serviceModel.getId()), serviceModel);
    }

    @Override
    public void delete(ServiceModel serviceModel) {
        services.remove(getServiceIndexById(serviceModel.getId()));
    }

    @Override
    public List<ServiceModel> getAll() {
        return services.stream()
                .sorted(Comparator.SERVICE_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getSortedByPrice() {
        return services.stream()
                .sorted(Comparator.SERVICE_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getSortedByDate(Date date) {
        return services.stream()
                .sorted(Comparator.SERVICE_DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getPriceBySection(ServicesSection section) {
        return null;
    }

    @Override
    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }
}
