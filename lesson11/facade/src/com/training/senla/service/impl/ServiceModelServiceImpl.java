package com.training.senla.service.impl;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.service.ServiceModelService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModelServiceImpl implements ServiceModelService {

    private static final Logger LOG = LogManager.getLogger(ServiceModelServiceImpl.class);

    private ServiceModelRepository serviceModelRepository;

    public ServiceModelServiceImpl() {
    }

    @Override
    public void addService(ServiceModel serviceModel) {
        try {
            serviceModelRepository.setService(serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public ServiceModel getService(int id) {
        ServiceModel service = null;
        try {
            service = serviceModelRepository.getService(id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return service;
    }

    @Override
    public void update(ServiceModel serviceModel) {
        try {
            serviceModelRepository.update(serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void delete(ServiceModel serviceModel) {
        try {
            serviceModelRepository.delete(serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public List<ServiceModel> getAll() {
        List<ServiceModel> services = null;
        try {
            services = serviceModelRepository.getAll();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<ServiceModel> getSortedByPrice() {
        List<ServiceModel> services = null;
        try {
            services = serviceModelRepository.getSortedByPrice();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<ServiceModel> getSortedByDate(Date date) {
        List<ServiceModel> services = null;
        try {
            services = serviceModelRepository.getSortedByDate(date);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Double> getPriceBySection(ServicesSection section) {
        List<Double> prices = null;
        try {
            prices =  serviceModelRepository.getPriceBySection(section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return prices;
    }

    public void setServiceModelRepository(ServiceModelRepository serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }
}
