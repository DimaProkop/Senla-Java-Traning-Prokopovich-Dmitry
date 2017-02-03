package com.training.senla.dao;

import com.training.senla.enums.RoomsSection;
import com.training.senla.model.Room;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface RoomDao extends BaseModelDao<Room>{

    int getCountFreeRooms(Session session);

    List<Room> getLatestGuests(Session session, int count);

    List<Room> getReleasedInFuture(Session session, Date date);

    List<Double> getPriceBySection(Session session, RoomsSection section);
}
