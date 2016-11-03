package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.storage.Storage;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModelRepositoryImpl implements ServiceModelRepository {

    private int currentId=1;

    public void calcCurrentId() {
        int maxId = 0;
        for (ServiceModel service : Storage.services) {
            if(service.getId() > maxId) {
                maxId = service.getId();
            }
        }
        currentId = maxId + 1;
    }

    public ServiceModelRepositoryImpl(List<ServiceModel> serviceModels) {
        Storage.services = serviceModels;
        calcCurrentId();
    }

    private int getServiceIndexById(int id) {
        for (int i = 0; i < Storage.services.size(); i++) {
            if(Storage.services.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setService(ServiceModel serviceModel) {
        serviceModel.setId(currentId++);
        Storage.services.add(serviceModel);
    }

    @Override
    public ServiceModel getService(int id) {
        return Storage.services.get(getServiceIndexById(id));
    }

    @Override
    public void update(ServiceModel serviceModel) {
        Storage.services.set(getServiceIndexById(serviceModel.getId()), serviceModel);
    }

    @Override
    public void delete(ServiceModel serviceModel) {
        Storage.services.remove(getServiceIndexById(serviceModel.getId()));
    }

    @Override
    public List<ServiceModel> getAll() {
        return Storage.services.stream()
                .sorted(Comparator.SERVICE_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getSortedByPrice() {
        return Storage.services.stream()
                .sorted(Comparator.SERVICE_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getSortedByDate(Date date) {
        return Storage.services.stream()
                .sorted(Comparator.SERVICE_DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getPriceBySection(ServicesSection section) {
        return null;
    }
}
