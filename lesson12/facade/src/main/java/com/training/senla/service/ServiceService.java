package com.training.senla.service;

import com.training.senla.enums.ServicesSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Service;
import com.training.senla.dao.ServiceDao;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceService {
    void addService(Service service);

    void addServiceToGuest(Guest guest, Service service);

    void removeServiceOfGuest(Guest guest, Service service);

    Service getService(int id);

    List<Service> getServices(Guest guest, SortType type);

    void update(Service service);

    void delete(Service service);

    List<Service> getAll(SortType type);

    List<Double> getPricesBySection(ServicesSection section);

    void setServiceDao(ServiceDao serviceDao);
}
