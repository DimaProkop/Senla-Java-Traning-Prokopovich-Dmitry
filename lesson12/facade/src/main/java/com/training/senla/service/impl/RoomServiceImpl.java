package com.training.senla.service.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.dao.GuestDao;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.dao.RoomDao;
import com.training.senla.service.RoomService;
import com.training.senla.util.connection.SessionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomServiceImpl implements RoomService {

    private static final Logger LOG = LogManager.getLogger(RoomServiceImpl.class);

    private RoomDao roomDao;
    private GuestDao guestDao;
    private RegistrationDao registrationDao;


    public RoomServiceImpl() {
    }

    @Override
    public void addRoom(Room room) {
        Session session = SessionManager.getInstance().getSession();
        try {
            roomDao.add(session, room);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public Room getRoom(int id) {
        Session session = SessionManager.getInstance().getSession();
        Room room = null;
        try {
            room = roomDao.getById(session, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return room;
    }

    @Override
    public void update(Room room) {
        Session session = SessionManager.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            roomDao.update(session, room);
            transaction.commit();
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public void delete(Room room) {
        Session session = SessionManager.getInstance().getSession();
        try {
            roomDao.delete(session, room);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public void registerGuest(Guest guest, Room room, Date startDate, Date finalDate) {
        Session session = SessionManager.getInstance().getSession();
        try {
            guest.setRoom(room);
            guestDao.update(session, guest);
            Registration registration = new Registration(guest.getId(), room.getId(), startDate, finalDate);
            registrationDao.add(session, registration);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public void evictGuest(Guest guest) {
        Session session = SessionManager.getInstance().getSession();
        try {
            guest.setRoom(null);
            guestDao.update(session, guest);
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
    }

    @Override
    public Room cloneRoom(int id) {
        Session session = SessionManager.getInstance().getSession();
        Room clone = null;
        try {
            Room room = roomDao.getById(session, id);
            clone = (Room) room.clone();
        }
        catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return clone;
    }

    @Override
    public List<Room> getAll(SortType type) {
        Session session = SessionManager.getInstance().getSession();
        List<Room> rooms = null;
        try {
            rooms = roomDao.getAll(session, type, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return rooms;
    }

    @Override
    public List<Room> getAllFree(SortType type) {
        Session session = SessionManager.getInstance().getSession();
        List<Room> rooms = null;
        try {
            rooms = roomDao.getAll(session, type, RoomStatus.FREE);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return rooms;
    }

    @Override
    public int getCountFreeRooms() {
        Session session = SessionManager.getInstance().getSession();
        int count = 0;
        try {
            count = roomDao.getCountFreeRooms(session);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return count;
    }

    @Override
    public List<Room> getReleasedInFuture(Date date) {
        Session session = SessionManager.getInstance().getSession();
        List<Room> rooms = new ArrayList<>();
        try {
            rooms = roomDao.getReleasedInFuture(session, date);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return rooms;
    }

    @Override
    public List<Room> getLatestGuests(int count) {
        Session session = SessionManager.getInstance().getSession();
        List<Room> rooms = null;
        try {
            rooms = roomDao.getLatestGuests(session, count);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(RoomsSection section) {
        Session session = SessionManager.getInstance().getSession();
        List<Double> prices = null;
        try {
            prices = roomDao.getPriceBySection(session, section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            SessionManager.getInstance().closeSession(session);
        }
        return prices;
    }

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    public void setGuestDao(GuestDao guestDao) {
        this.guestDao = guestDao;
    }

    public void setRegistrationDao(RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }
}
