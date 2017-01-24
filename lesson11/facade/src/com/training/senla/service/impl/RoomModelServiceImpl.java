package com.training.senla.service.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.repository.RoomModelRepository;
import com.training.senla.service.RoomModelService;
import com.training.senla.util.connection.ConnectionManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModelServiceImpl implements RoomModelService {

    private static final Logger LOG = LogManager.getLogger(RoomModelServiceImpl.class);

    private RoomModelRepository roomModelRepository;
    private GuestModelRepository guestModelRepository;
    private RegistrationModelRepository registrationModelRepository;

    public RoomModelServiceImpl() {
    }

    @Override
    public void addRoom(RoomModel roomModel) {
        Connection connection = ConnectionManager.getConnection();
        try {
            roomModelRepository.set(connection, roomModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public RoomModel getRoom(int id) {
        RoomModel room = null;
        Connection connection = ConnectionManager.getConnection();
        try {
            room = roomModelRepository.get(connection, id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return room;
    }

    @Override
    public void update(RoomModel roomModel) {
        Connection connection = ConnectionManager.getConnection();
        boolean status = false;
        try {
            connection.setAutoCommit(false);
            status = roomModelRepository.update(connection, roomModel);
            if(status) {
                connection.commit();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Override
    public void delete(RoomModel roomModel) {
        Connection connection = ConnectionManager.getConnection();
        try {
            roomModelRepository.delete(connection, roomModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void registerGuest(GuestModel guestModel, RoomModel roomModel, Date startDate, Date finalDate) {
        Connection connection = ConnectionManager.getConnection();
        try {
            roomModel.addGuest(guestModel);
            guestModel.setRoomModel(roomModel);
            guestModelRepository.update(connection, guestModel);
            registrationModelRepository.set(connection, new RegistrationModel(guestModel.getId(), roomModel.getId(), startDate, finalDate));
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void evictGuest(GuestModel guestModel) {
        Connection connection = ConnectionManager.getConnection();
        try {
            guestModelRepository.delete(connection, guestModel);
            guestModel.getRoomModel().removeGuest(guestModel);
            guestModelRepository.update(connection, guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public RoomModel cloneRoom(int id) {
        Connection connection = ConnectionManager.getConnection();
        RoomModel room = roomModelRepository.get(connection, id);
        RoomModel clone = null;
        try {
            clone = (RoomModel) room.clone();
        } catch (CloneNotSupportedException e) {
            LOG.error(e);
        }
        return clone;
    }

    @Override
    public List<RoomModel> getAll() {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getAll(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByPrice(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByCapacity(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByRating(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getAll(connection, status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByPrice(connection, status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByCapacity(connection, status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByRating(connection, status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public int getCountFreeRooms() {
        Connection connection = ConnectionManager.getConnection();
        int count = 0;
        try {
            count = roomModelRepository.getCountFreeRooms(connection);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }

    @Override
    public List<RoomModel> getReleasedInFuture(Date date) {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = new ArrayList<>();
        try {
            List<Integer> roomIds = registrationModelRepository.getAll(connection).stream()
                    .filter(x->x.getFinalDate().getTime() < date.getTime())
                    .map(RegistrationModel::getRoomId)
                    .distinct()
                    .collect(Collectors.toList());
            for (int i = 0; i < roomModelRepository.getAll(connection).size(); i++) {
                RoomModel room = roomModelRepository.get(connection, roomIds.get(i));
                rooms.add(room);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getLatestGuests(int count) {
        Connection connection = ConnectionManager.getConnection();
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getLatestGuests(connection, count);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(RoomsSection section) {
        Connection connection = ConnectionManager.getConnection();
        List<Double> prices = null;
        try {
            prices = roomModelRepository.getPriceBySection(connection, section);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return prices;
    }

    public void setRoomModelRepository(RoomModelRepository roomModelRepository) {
        this.roomModelRepository = roomModelRepository;
    }

    public void setGuestModelRepository(GuestModelRepository guestModelRepository) {
        this.guestModelRepository = guestModelRepository;
    }

    public void setRegistrationModelRepository(RegistrationModelRepository registrationModelRepository) {
        this.registrationModelRepository = registrationModelRepository;
    }
}
