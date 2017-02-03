package com.training.senla.service.impl;

import com.training.senla.dao.ServiceDao;
import com.training.senla.enums.ServicesSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Service;
import com.training.senla.service.ServiceService;
import com.training.senla.util.connection.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Session session = SessionManager.getInstance().getSession();
        try {
            serviceDao.add(session, service);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public void addServiceToGuest(Guest guest, Service service) {
        Session session = SessionManager.getInstance().getSession();
        try {
            service.setGuest(guest);
            serviceDao.update(session, service);
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public void removeServiceOfGuest(Guest guest, Service service) {
        Session session = SessionManager.getInstance().getSession();
        try {
            service.setGuest(null);
            serviceDao.update(session, service);
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public Service getService(int id) {
        Session session = SessionManager.getInstance().getSession();
        Service service = null;
        try {
            service = serviceDao.getById(session, id);
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return service;
    }

    @Override
    public void update(Service service) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            serviceDao.update(session, service);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public void delete(Service service) {
        Session session = SessionManager.getInstance().getSession();
        try {
            serviceDao.delete(session, service);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public List<Service> getAll(SortType type) {
        Session session = SessionManager.getInstance().getSession();
        List<Service> services = null;
        try {
            services = serviceDao.getAll(session, type, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return services;
    }

    @Override
    public List<Double> getPricesBySection(ServicesSection section) {
        Session session = SessionManager.getInstance().getSession();
        List<Double> prices = null;
        try {
            prices = serviceDao.getPriceBySection(session, section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return prices;
    }

    public void setServiceDao(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }
}
