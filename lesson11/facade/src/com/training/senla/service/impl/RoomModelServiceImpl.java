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

    public RoomModelServiceImpl(RoomModelRepository roomModelRepository, GuestModelRepository guestModelRepository, RegistrationModelRepository registrationModelRepository) {
        this.roomModelRepository = roomModelRepository;
        this.guestModelRepository = guestModelRepository;
        this.registrationModelRepository = registrationModelRepository;
    }

    @Override
    public void addRoom(RoomModel roomModel) {
        try {
            roomModelRepository.addRoom(roomModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public RoomModel getRoom(int id) {
        RoomModel room = null;
        try {
            room = roomModelRepository.getRoom(id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return room;
    }

    @Override
    public void update(RoomModel roomModel) {
        try {
            roomModelRepository.update(roomModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void delete(RoomModel roomModel) {
        try {
            roomModelRepository.delete(roomModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void registerGuest(GuestModel guestModel, RoomModel roomModel, Date startDate, Date finalDate) {
        try {
            roomModel.addGuest(guestModel);
            guestModel.setRoomModel(roomModel);
            registrationModelRepository.addRecord(new RegistrationModel(guestModel.getId(), roomModel.getId(), startDate, finalDate));
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
            this.roomModelRepository.update(guestModel.getRoomModel());
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public RoomModel cloneRoom(int id) {
        RoomModel room = roomModelRepository.getRoom(id);
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
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getAll();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByPrice();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByCapacity();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByRating();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getAll(status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByPrice(status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByCapacity(status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getSortedByRating(status);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public int getCountFreeRooms() {
        int count = 0;
        try {
            count = roomModelRepository.getCountFreeRooms();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }

    @Override
    public List<RoomModel> getReleasedInFuture(Date date) {
        List<RoomModel> rooms = new ArrayList<>();
        try {
            List<Integer> roomIds = registrationModelRepository.getAll().stream()
                    .filter(x->x.getFinalDate().getTime() < date.getTime())
                    .map(RegistrationModel::getRoomId)
                    .distinct()
                    .collect(Collectors.toList());
            for (int i = 0; i < roomModelRepository.getAll().size(); i++) {
                RoomModel room = roomModelRepository.getRoom(roomIds.get(i));
                rooms.add(room);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<RoomModel> getLatestGuests(int count) {
        List<RoomModel> rooms = null;
        try {
            rooms = roomModelRepository.getLatestGuests(count);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(RoomsSection section) {
        List<Double> prices = null;
        try {
            prices = roomModelRepository.getPriceBySection(section);
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
