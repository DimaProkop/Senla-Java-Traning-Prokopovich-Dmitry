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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public RoomModelServiceImpl(RoomModelRepository roomModelRepository, GuestModelRepository guestModelRepository, RegistrationModelRepository registrationModelRepository) {
        this.roomModelRepository = roomModelRepository;
        this.guestModelRepository = guestModelRepository;
        this.registrationModelRepository = registrationModelRepository;
    }

    @Override
    public void setRoom(RoomModel roomModel) {
        try {
            roomModelRepository.setRoom(roomModel);
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
    public void registerGuest(GuestModel guestModel, RoomModel roomModel, LocalDate startDate, LocalDate finalDate) {
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
        try {
            guestModelRepository.delete(guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
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
    public List<RoomModel> getReleasedInFuture(LocalDate date) {
        List<RoomModel> rooms = new ArrayList<>();
        try {
            List<Integer> roomIds = registrationModelRepository.getAll().stream()
                    .filter(x->x.getFinalDate().getDayOfYear() < date.getDayOfYear())
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
}