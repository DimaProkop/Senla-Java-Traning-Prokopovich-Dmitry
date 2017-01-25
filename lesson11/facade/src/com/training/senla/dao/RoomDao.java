package com.training.senla.dao;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.Room;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface RoomDao{

    int getCountFreeRooms(Connection connection);

    List<Room> getLatestGuests(Connection connection, int count);

    List<Room> getReleasedInFuture(Connection connection, Date date);

    List<Double> getPriceBySection(Connection connection, RoomsSection section);
}
