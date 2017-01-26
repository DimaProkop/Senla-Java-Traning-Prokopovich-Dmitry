package com.training.senla.service.impl;

import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.dao.GuestDao;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.dao.RoomDao;
import com.training.senla.service.RoomService;
import com.training.senla.util.connection.ConnectionManager;
import com.training.senla.util.db.LibraryQueries;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomServiceImpl implements RoomService {

    private static final Logger LOG = LogManager.getLogger(RoomServiceImpl.class);

    private RoomDao roomDao;
    private GuestDao guestDao;
    private RegistrationDao registrationDao;
    private LibraryQueries library;


    public RoomServiceImpl() {
        library = new LibraryQueries();
    }

    @Override
    public void addRoom(Room room) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.set(connection, room);
            roomDao.set(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Room getRoom(int id) {
        Room room = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.get(library.GET_ROOM, connection, id);
            room = roomDao.get(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return room;
    }

    @Override
    public void update(Room room) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        boolean status = false;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = library.update(connection, room);
            status = roomDao.update(statement);
            if(status) {
                connection.commit();
            }
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException sql) {
                LOG.error(sql.getMessage());
            }
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void delete(Room room) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            PreparedStatement statement = library.delete(connection, room);
            roomDao.delete(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void registerGuest(Guest guest, Room room, Date startDate, Date finalDate) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            guest.setRoom(room);
            PreparedStatement preparedStatement = library.update(connection, guest);
            guestDao.update(preparedStatement);
            Registration registration = new Registration(guest.getId(), room.getId(), startDate, finalDate);
            PreparedStatement statement = library.set(connection, registration);
            registrationDao.set(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void evictGuest(Guest guest) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            guest.setRoom(null);
            PreparedStatement statement = library.update(connection, guest);
            guestDao.update(statement);
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Room cloneRoom(int id) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement statement = library.get(library.GET_ROOM, connection, id);
        Room room = roomDao.get(statement);
        Room clone = null;
        try {
            clone = (Room) room.clone();
        } catch (CloneNotSupportedException e) {
            LOG.error(e);
        }
        return clone;
    }

    @Override
    public List<Room> getAll(SortType type) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            PreparedStatement statement = library.getAll(library.GET_SORT_ROOM, connection, type);
            rooms = roomDao.getAll(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getAllFree(SortType type) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            PreparedStatement statement = library.getAll(library.GET_SORT_ROOM_FREE, connection, type);
            rooms = roomDao.getAll(statement);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public int getCountFreeRooms() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        int count = 0;
        try {
            count = roomDao.getCountFreeRooms(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }

    @Override
    public List<Room> getReleasedInFuture(Date date) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = new ArrayList<>();
        try {
//            List<Integer> roomIds = registrationDao.getAll(connection).stream()
//                    .filter(x->x.getFinalDate().getTime() < date.getTime())
//                    .map(Registration::getRoomId)
//                    .distinct()
//                    .collect(Collectors.toList());
//            for (int i = 0; i < roomDao.getAll(connection).size(); i++) {
//                Room room = roomDao.get(connection, roomIds.get(i));
//                rooms.add(room);
//            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getLatestGuests(int count) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomDao.getLatestGuests(connection, count);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(RoomsSection section) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Double> prices = null;
        try {
            prices = roomDao.getPriceBySection(connection, section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
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
