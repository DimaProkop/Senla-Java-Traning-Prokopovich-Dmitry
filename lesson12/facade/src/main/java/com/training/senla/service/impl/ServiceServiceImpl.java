package com.training.senla.service.impl;

import com.training.senla.dao.ServiceDao;
import com.training.senla.enums.ServicesSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Service;
import com.training.senla.service.ServiceService;
import com.training.senla.util.connection.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceServiceImpl implements ServiceService {

    private static final Logger LOG = LogManager.getLogger(ServiceServiceImpl.class);

    private ServiceDao serviceDao;

    public ServiceServiceImpl() {
    }

    @Override
    public void addService(Service service) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            serviceDao.add(connection, service);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Service getService(int id) {
        Service service = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            service = serviceDao.getById(connection, id);
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
            status = serviceDao.update(connection, service);
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
            serviceDao.delete(connection, service);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public List<Service> getAll(SortType type) {
        List<Service> services = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            services = serviceDao.getAll(connection, type, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<Double> getPricesBySection(ServicesSection section) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Double> prices = null;
        try {
            prices = serviceDao.getPriceBySection(connection, section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return prices;
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
}
