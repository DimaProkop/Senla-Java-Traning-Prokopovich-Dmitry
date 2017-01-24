package com.training.senla.service.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.service.GuestModelService;
import com.training.senla.util.connection.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelServiceImpl implements GuestModelService {

    private static final Logger LOG = LogManager.getLogger(GuestModelServiceImpl.class);

    private GuestModelRepository guestModelRepository;

    public GuestModelServiceImpl() {
    }

    @Override
    public void addGuest(GuestModel guestModel) {
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            guestModelRepository.set(connection, guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public GuestModel getGuest(int id) {
        GuestModel guest = null;
        Connection connection = null;
        try {
            connection = ConnectionManager.getConnection();
            guest = guestModelRepository.get(connection, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return guest;
    }

    @Override
    public void update(GuestModel guestModel) {
        Connection connection = ConnectionManager.getConnection();
        boolean status = false;
        try {
            connection.setAutoCommit(false);
            status = guestModelRepository.update(connection, guestModel);
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
    public void delete(GuestModel guestModel) {
        Connection connection = ConnectionManager.getConnection();
        try {
            guestModelRepository.delete(connection, guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public void addService(GuestModel guestModel, ServiceModel serviceModel) {
        try {
            guestModel.addService(serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void removeService(GuestModel guestModel, ServiceModel serviceModel) {
        try {
            guestModel.removeService(serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public List<ServiceModel> getServicesByPrice(GuestModel guestModel) {
        List<ServiceModel> services = null;
        Connection connection = ConnectionManager.getConnection();
        try {
            services = guestModelRepository.getServicesByPrice(connection, guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return services;
    }

    @Override
    public List<ServiceModel> getServicesByDate(GuestModel guestModel) {
        List<ServiceModel> services = null;
        Connection connection = ConnectionManager.getConnection();
        try {
            services = guestModelRepository.getServicesByDate(connection, guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return services;
    }

    @Override
    public List<GuestModel> getAll() {
        Connection connection = ConnectionManager.getConnection();
        List<GuestModel> guests = null;
        try {
            guests = guestModelRepository.getAll(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return guests;
    }

    @Override
    public List<GuestModel> getSortedByFinalDate() {
        Connection connection = ConnectionManager.getConnection();
        List<GuestModel> guests = null;
        try {
            guests = guestModelRepository.getSortedByFinalDate(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return guests;
    }

    @Override
    public List<GuestModel> getSortedByName() {
        List<GuestModel> guests = null;
        Connection connection = ConnectionManager.getConnection();
        try {
            guests = guestModelRepository.getSortedByName(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return guests;
    }

    @Override
    public double getSumByRoom(RoomModel roomModel, GuestModel guestModel) {
        double sum = 0;
        Connection connection = ConnectionManager.getConnection();
        try {
            sum = guestModelRepository.getSumByRoom(connection, roomModel, guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return sum;
    }

    @Override
    public int getCount() {
        int count = 0;
        Connection connection = ConnectionManager.getConnection();
        try {
            count = guestModelRepository.getCount(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
        return count;
    }

    public void setGuestModelRepository(GuestModelRepository guestModelRepository) {
        this.guestModelRepository = guestModelRepository;
    }
}
