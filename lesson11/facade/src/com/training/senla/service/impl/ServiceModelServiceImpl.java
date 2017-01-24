package com.training.senla.service.impl;

import com.training.senla.enums.ServicesSection;
import com.training.senla.model.ServiceModel;
import com.training.senla.dao.ServiceModelDao;
import com.training.senla.service.ServiceModelService;
import com.training.senla.util.connection.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceModelServiceImpl implements ServiceModelService {

    private static final Logger LOG = LogManager.getLogger(ServiceModelServiceImpl.class);

    private ServiceModelDao serviceModelRepository;

    public ServiceModelServiceImpl() {
    }

    @Override
    public void addService(ServiceModel serviceModel) {
        Connection connection = ConnectionManager.getConnection();
        try {
            serviceModelRepository.set(connection, serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public ServiceModel getService(int id) {
        ServiceModel service = null;
        Connection connection = ConnectionManager.getConnection();
        try {
            service = serviceModelRepository.get(connection, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return service;
    }

    @Override
    public void update(ServiceModel serviceModel) {
        Connection connection = ConnectionManager.getConnection();
        boolean status = false;
        try {
            connection.setAutoCommit(false);
            status = serviceModelRepository.update(connection, serviceModel);
            if(status) {
                connection.commit();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public void delete(ServiceModel serviceModel) {
        Connection connection = ConnectionManager.getConnection();
        try {
            serviceModelRepository.delete(connection, serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public List<ServiceModel> getAll() {
        List<ServiceModel> services = null;
        Connection connection = ConnectionManager.getConnection();
        try {
            services = serviceModelRepository.getAll(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return services;
    }

    @Override
    public List<ServiceModel> getSortedByPrice() {
        Connection connection = ConnectionManager.getConnection();
        List<ServiceModel> services = null;
        try {
            services = serviceModelRepository.getSortedByPrice(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return services;
    }

    @Override
    public List<ServiceModel> getSortedByDate(Date date) {
        List<ServiceModel> services = null;
        Connection connection = ConnectionManager.getConnection();
        try {
            services = serviceModelRepository.getSortedByDate(connection, date);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return services;
    }

    @Override
    public List<Double> getPriceBySection(ServicesSection section) {
        Connection connection = ConnectionManager.getConnection();
        List<Double> prices = null;
        try {
            prices =  serviceModelRepository.getPriceBySection(connection, section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return prices;
    }

    public void setServiceModelRepository(ServiceModelDao serviceModelRepository) {
        this.serviceModelRepository = serviceModelRepository;
    }
}
