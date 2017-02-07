package com.training.senla.dao;

import com.training.senla.enums.ServicesSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Service;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceDao extends BaseModelDao<Service>{
    List<Double> getPriceBySection(Session session, ServicesSection section) throws Exception;

    List<Service> getServices(Session session, Guest guest, SortType type) throws Exception;
}
