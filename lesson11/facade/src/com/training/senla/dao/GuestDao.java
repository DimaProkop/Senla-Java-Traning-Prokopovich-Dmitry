package com.training.senla.dao;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;

import java.sql.Connection;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface GuestDao extends BaseModelDao<Guest>{

    List<Service> getServices(Connection connection, Guest guest, SortType type);

    List<Guest> getSortedByFinalDate(Connection connection);

    double getSumByRoom(Connection connection, Room room, Guest guest);

    int getCount(Connection connection);
}
