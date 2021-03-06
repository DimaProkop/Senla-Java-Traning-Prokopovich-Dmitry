package com.training.senla.dao.impl;

import com.training.senla.dao.RoomDao;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


/**
 * Created by prokop on 13.10.16.
 */
public class RoomDaoImpl extends BaseModelDaoImpl<Room> implements RoomDao {

    private static final Logger LOG = LogManager.getLogger(RoomDaoImpl.class);


    public RoomDaoImpl() {
        super(Room.class);
    }


    @Override
    public int getCountFreeRooms(Session session) {
        int count = 0;
        try {
            count = ((Long) getCriteria(session)
                    .add(Restrictions.eq("status", RoomStatus.FREE))
                    .setProjection(Projections.rowCount())
                    .uniqueResult()).intValue();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }

    @Override
    public List<Room> getLatestGuests(Session session, int count) {
        List<Room> rooms = new ArrayList<>();
        try {
            DetachedCriteria ownerCriteria = DetachedCriteria.forClass(Registration.class);
            ownerCriteria.setProjection(Projections.property("roomId"));

            Criteria criteria = getCriteria(session);
            criteria.add(Property.forName("id").in(ownerCriteria));

            Criteria rowsRoom = session.createCriteria(Registration.class);
            rowsRoom.setProjection(Projections.property("roomId"));
            int max = ((Long) rowsRoom.setProjection(Projections.rowCount()).uniqueResult()).intValue();

            rooms = criteria.setFirstResult(max-count).setMaxResults(count).list();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getReleasedInFuture(Session session, Date date) {
        List<Room> rooms = new ArrayList<>();
        try {
            rooms = session.createQuery("select r from Room r, Registration reg where reg.roomId = r.id and reg.finalDate <:currentDate")
                    .setParameter("currentDate", date)
                    .list();

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(Session session, RoomsSection section) {
        List<Double> prices = new ArrayList<>();
        try {
            Criteria criteria = session.createCriteria(Room.class);
            prices = criteria.setProjection(Projections.groupProperty("price"))
                    .add(Restrictions.eq("section", section))
                    .list();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return prices;
    }

}
