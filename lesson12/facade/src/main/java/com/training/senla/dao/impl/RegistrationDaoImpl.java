package com.training.senla.dao.impl;

import com.training.senla.dao.RegistrationDao;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationDaoImpl extends BaseModelDaoImpl<Registration> implements RegistrationDao {
    private static final Logger LOG = LogManager.getLogger(RegistrationDaoImpl.class);


    public RegistrationDaoImpl() {
        super(Registration.class);
    }

    @SuppressWarnings("deprecation")
    @Override
    public double getSumByRoom(Session session, Room room, Guest guest) throws Exception{

        Criteria criteria = getCriteria(session);
        Criterion guestId = Restrictions.eq("guestId", guest.getId());
        Criterion roomId = Restrictions.eq("roomId", room.getId());
        List<Registration> registrations = criteria.add(Restrictions.and(guestId, roomId)).list();
        int finalDate = registrations.get(0).getFinalDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfYear();
        int startDate = registrations.get(0).getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfYear();
        return (finalDate - startDate) * room.getPrice();
    }


    @Override
    public List<Guest> getSortedByFinalDate(Session session) throws Exception{
        List<Guest> guests = new ArrayList<>();
            guests = session.createQuery("select g from Guest g inner join Registration reg ON reg.guestId = g.id order by reg.finalDate")
                    .list();

        return guests;
    }
}
