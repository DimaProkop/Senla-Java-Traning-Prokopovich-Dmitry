package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.model.GuestModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelRepositoryImpl implements GuestModelRepository {

    private List<GuestModel> guests;

    public GuestModelRepositoryImpl() {
    }

    private int getGuestIndexById(int id) {
        for (int i = 0; i < guests.size(); i++) {
            if(guests.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<ServiceModel> getServicesByDate(GuestModel guestModel) {
        return guests.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getServicesByPrice(GuestModel guestModel) {
        return guests.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getAll() {
        return guests.stream()
                .sorted(Comparator.GUEST_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getSortedByName() {
        return guests.stream()
                .sorted(Comparator.GUEST_NAME_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int getCount() {
        return guests.size();
    }

    @Override
    public void update(Connection connection, GuestModel entity) {

    }

    @Override
    public GuestModel get(Connection connection, int id) {
        return null;
    }

    @Override
    public void set(Connection connection, GuestModel entity) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT guest(name) VALUES ("+entity.getName()+")");

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GuestModel> getAll(Connection connection) {
        return null;
    }

    @Override
    public void delete(Connection connection, GuestModel entity) {

    }
}
