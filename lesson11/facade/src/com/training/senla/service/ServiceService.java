package com.training.senla.service;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.Service;
import com.training.senla.dao.ServiceDao;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceService {
    void addService(Service service);

    Service getService(int id);

    void update(Service service);

    void delete(Service service);

    List<Service> getAll();

    List<Service> getSortedByPrice();

    List<Service> getSortedByDate(Date date);

    List<Double> getPriceBySection(ServicesSection section);

    void setServiceRepository(ServiceDao serviceRepository);
}
