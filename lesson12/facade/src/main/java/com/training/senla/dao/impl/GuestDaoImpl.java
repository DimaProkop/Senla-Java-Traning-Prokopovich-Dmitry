package com.training.senla.dao.impl;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by prokop on 13.10.16.
 */
public class GuestDaoImpl extends BaseModelDaoImpl<Guest> implements GuestDao{
    private static final Logger LOG = LogManager.getLogger(GuestDaoImpl.class);


    public GuestDaoImpl() {
    }

    @Override
    public List<Service> getServices(Session session, Guest guest, SortType type) {
        List<Service> services = new ArrayList<>();
        try {
            Criteria criteria = session.createCriteria(Service.class);
            services = criteria.add(Restrictions.eq("guestId", guest.getId()))
                    .addOrder(Order.asc(type.toString())).list();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        return services;
    }


    @Override
    public List<Guest> getSortedByFinalDate(Session session) {
        List<Guest> guests = new ArrayList<>();
        try {
            guests = session.createQuery("select g from Guest g inner join Registration reg ON reg.guestId = g.id order by reg.finalDate")
                    .list();
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guests;
    }

    @SuppressWarnings("deprecation")
    @Override
    public double getSumByRoom(Session session, Room room, Guest guest) {
        double sum = 0;
        try {
            Criteria criteria = session.createCriteria(Registration.class);
            Criterion guestId = Restrictions.eq("guestId", guest.getId());
            Criterion roomId = Restrictions.eq("roomId", room.getId());
            List<Registration> registrations = criteria.add(Restrictions.and(guestId, roomId)).list();
            int finalDate = registrations.get(0).getFinalDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfYear();
            int startDate = registrations.get(0).getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfYear();
            sum = (finalDate - startDate) * room.getPrice();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return sum;
    }

    @Override
    public int getCount(Session session) {
        int count = 0;
        try {
            count = ((Long) session.createCriteria(Guest.class)
                    .setProjection(Projections.rowCount())
                    .uniqueResult()).intValue();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }

    @Override
    protected Class assignClass() throws SQLException {
        return Guest.class;
    }
}
