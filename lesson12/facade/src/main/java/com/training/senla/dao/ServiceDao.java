package com.training.senla.dao;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.Service;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceDao extends BaseModelDao<Service>{
    List<Double> getPriceBySection(Session session, ServicesSection section);
}
