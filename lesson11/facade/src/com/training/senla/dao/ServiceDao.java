package com.training.senla.dao;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.Service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface ServiceDao{
    List<Double> getPriceBySection(Connection connection, ServicesSection section);
}
