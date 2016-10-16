package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModelRepositoryImpl implements ServiceModelRepository {

    private List<ServiceModel> serviceModels;
    public static int currentId=1;

    public ServiceModelRepositoryImpl(List<ServiceModel> serviceModels) {
        this.serviceModels = serviceModels;
    }

    private int getServiceIndexById(int id) {
        for (int i = 0; i < this.serviceModels.size(); i++) {
            if(this.serviceModels.get(i).getServiceId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setService(ServiceModel serviceModel) {
        serviceModel.setServiceId(currentId++);
        this.serviceModels.add(serviceModel);
    }

    @Override
    public ServiceModel getService(int id) {
        return this.serviceModels.get(getServiceIndexById(id));
    }

    @Override
    public void update(ServiceModel serviceModel) {
        this.serviceModels.set(getServiceIndexById(serviceModel.getServiceId()), serviceModel);
    }

    @Override
    public void delete(ServiceModel serviceModel) {
        this.serviceModels.remove(getServiceIndexById(serviceModel.getServiceId()));
    }

    @Override
    public void setAll(List<ServiceModel> serviceModels) {
        this.serviceModels.addAll(serviceModels);
    }

    @Override
    public List<ServiceModel> getAll() {
        return this.serviceModels.stream()
                .sorted(Comparator.SERVICE_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getSortedByPrice() {
        return this.serviceModels.stream()
                .sorted(Comparator.SERVICE_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getSortedByDate(LocalDate date) {
        return this.serviceModels.stream()
                .sorted(Comparator.SERVICE_DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getPriceBySection(ServicesSection section) {
        return null;
    }
}
