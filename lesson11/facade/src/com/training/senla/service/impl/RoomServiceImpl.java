package com.training.senla.service.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.dao.GuestDao;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.dao.RoomDao;
import com.training.senla.service.RoomService;
import com.training.senla.util.connection.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
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

    private RoomDao roomRepository;
    private GuestDao guestRepository;
    private RegistrationDao registrationRepository;

    public RoomServiceImpl() {
    }

    @Override
    public void addRoom(Room room) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            roomRepository.set(connection, room);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Room getRoom(int id) {
        Room room = null;
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            room = roomRepository.get(connection, id);
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
            status = roomRepository.update(connection, room);
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
            roomRepository.delete(connection, room);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void registerGuest(Guest guest, Room room, Date startDate, Date finalDate) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            guest.setRoom(room);
            guestRepository.update(connection, guest);
            registrationRepository.set(connection, new Registration(guest.getId(), room.getId(), startDate, finalDate));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void evictGuest(Guest guest) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        try {
            guestRepository.delete(connection, guest);
            guestRepository.update(connection, guest);
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public Room cloneRoom(int id) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        Room room = roomRepository.get(connection, id);
        Room clone = null;
        try {
            clone = (Room) room.clone();
        } catch (CloneNotSupportedException e) {
            LOG.error(e);
        }
        return clone;
    }

    @Override
    public List<Room> getAll() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomRepository.getAll(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByPrice() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomRepository.getSortedByPrice(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByCapacity() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomRepository.getSortedByCapacity(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByRating() {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomRepository.getSortedByRating(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getAll(RoomStatus status) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomRepository.getAll(connection, status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByPrice(RoomStatus status) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomRepository.getSortedByPrice(connection, status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByCapacity(RoomStatus status) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomRepository.getSortedByCapacity(connection, status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByRating(RoomStatus status) {
        Connection connection = ConnectionManager.getInstance().getConnection();
        List<Room> rooms = null;
        try {
            rooms = roomRepository.getSortedByRating(connection, status);
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
            count = roomRepository.getCountFreeRooms(connection);
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
            List<Integer> roomIds = registrationRepository.getAll(connection).stream()
                    .filter(x->x.getFinalDate().getTime() < date.getTime())
                    .map(Registration::getRoomId)
                    .distinct()
                    .collect(Collectors.toList());
            for (int i = 0; i < roomRepository.getAll(connection).size(); i++) {
                Room room = roomRepository.get(connection, roomIds.get(i));
                rooms.add(room);
            }
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
            rooms = roomRepository.getLatestGuests(connection, count);
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
            prices = roomRepository.getPriceBySection(connection, section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return prices;
    }

    public void setRoomRepository(RoomDao roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void setGuestRepository(GuestDao guestRepository) {
        this.guestRepository = guestRepository;
    }

    public void setRegistrationRepository(RegistrationDao registrationRepository) {
        this.registrationRepository = registrationRepository;
    }
}
