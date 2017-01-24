package com.training.senla.dao;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.sql.Connection;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface GuestModelDao extends BaseModelDao<GuestModel> {

    List<ServiceModel> getServicesByPrice(Connection connection, GuestModel guestModel);

    List<ServiceModel> getServicesByDate(Connection connection, GuestModel guestModel);

    List<GuestModel> getSortedByName(Connection connection);

    List<GuestModel> getSortedByFinalDate(Connection connection);

    double getSumByRoom(Connection connection, RoomModel roomModel, GuestModel guestModel);

    int getCount(Connection connection);
}
