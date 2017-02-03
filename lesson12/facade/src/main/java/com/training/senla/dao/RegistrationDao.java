package com.training.senla.dao;

import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import org.hibernate.Session;

import java.util.List;


/**
 * Created by prokop on 16.10.16.
 */
public interface RegistrationDao extends BaseModelDao<Registration>{

    List<Guest> getSortedByFinalDate(Session session);

    double getSumByRoom(Session session, Room room, Guest guest);

}
