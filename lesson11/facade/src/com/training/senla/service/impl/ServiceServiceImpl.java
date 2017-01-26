package com.training.senla.service.impl;

import com.training.senla.dao.ServiceDao;
import com.training.senla.enums.SortType;
import com.training.senla.model.Service;
import com.training.senla.service.ServiceService;
import com.training.senla.util.connection.ConnectionManager;
import com.training.senla.util.db.LibraryQueries;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceServiceImpl implements ServiceService {

    private static final Logger LOG = LogManager.getLogger(ServiceServiceImpl.class);

    private ServiceDao serviceDao;
    private LibraryQueries library;

    public ServiceServiceImpl() {
        library = new LibraryQueries();
    }

    @Override
    public void addService(Service service) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.set(connection, service);
            serviceDao.set(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Service getService(int id) {
        Service service = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.get(library.GET_SERVICE, connection, id);
            service = serviceDao.get(statement);
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
            PreparedStatement statement = library.update(connection, service);
            status = serviceDao.update(statement);
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
            PreparedStatement statement = library.delete(connection, service);
            serviceDao.delete(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public List<Service> getAll(SortType type) {
        List<Service> services = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.getAll(library.GET_SORT_SERVICE, connection, type);
            services = serviceDao.getAll(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
}
