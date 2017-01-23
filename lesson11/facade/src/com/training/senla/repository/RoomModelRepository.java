package com.training.senla.repository;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.RoomModel;

import java.sql.Connection;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface RoomModelRepository extends BaseModelRepository<RoomModel>{

    List<RoomModel> getSortedByPrice(Connection connection);

    List<RoomModel> getSortedByCapacity(Connection connection);

    List<RoomModel> getSortedByRating(Connection connection);

    //for free rooms
    List<RoomModel> getAll(Connection connection, RoomStatus status);

    List<RoomModel> getSortedByPrice(Connection connection, RoomStatus status);

    List<RoomModel> getSortedByCapacity(Connection connection, RoomStatus status);

    List<RoomModel> getSortedByRating(Connection connection, RoomStatus status);

    int getCountFreeRooms(Connection connection);

    List<RoomModel> getLatestGuests(Connection connection, int count);

    List<Double> getPriceBySection(Connection connection, RoomsSection section);
}
