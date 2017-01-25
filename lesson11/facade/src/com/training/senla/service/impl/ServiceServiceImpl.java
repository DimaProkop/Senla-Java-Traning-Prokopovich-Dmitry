package com.training.senla.service.impl;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.Service;
import com.training.senla.dao.ServiceDao;
import com.training.senla.service.ServiceService;
import com.training.senla.util.connection.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceServiceImpl implements ServiceService {

    private static final Logger LOG = LogManager.getLogger(ServiceServiceImpl.class);

    private ServiceDao serviceRepository;

    public ServiceServiceImpl() {
    }

    @Override
    public void addService(Service service) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            serviceRepository.set(connection, service);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Service getService(int id) {
        Service service = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            service = serviceRepository.get(connection, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return service;
    }

    @Override
    public void update(Service service) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        boolean status = false;
        try {
            connection.setAutoCommit(false);
            status = serviceRepository.update(connection, service);
            if(status) {
                connection.commit();
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sql) {
                LOG.error(sql.getMessage());
            }
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void delete(Service service) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            serviceRepository.delete(connection, service);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public List<Service> getAll() {
        List<Service> services = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            services = serviceRepository.getAll(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Service> getSortedByPrice() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Service> services = null;
        try {
            services = serviceRepository.getSortedByPrice(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Service> getSortedByDate(Date date) {
        List<Service> services = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            services = serviceRepository.getSortedByDate(connection, date);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Double> getPriceBySection(ServicesSection section) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Double> prices = null;
        try {
            prices =  serviceRepository.getPriceBySection(connection, section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return prices;
    }

    public void setServiceRepository(ServiceDao serviceRepository) {
        this.serviceRepository = serviceRepository;
    }
}
