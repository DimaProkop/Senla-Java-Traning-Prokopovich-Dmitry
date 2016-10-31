package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.RoomModelRepository;
import com.training.senla.storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModelRepositoryImpl implements RoomModelRepository {

    private int currentId=1;

    public void calcCurrentId() {
        int maxId = 0;
        for (RoomModel room : Storage.rooms) {
            if(room.getId() > maxId) {
                maxId = room.getId();
            }
        }
        currentId = maxId + 1;
    }

    public RoomModelRepositoryImpl(List<RoomModel> roomModels) {
        Storage.rooms = roomModels;
        calcCurrentId();
    }

    private int getRoomIndexById(int id) {
        for (int i = 0; i < Storage.rooms.size(); i++) {
            if(Storage.rooms.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setRoom(RoomModel roomModel) {
        roomModel.setId(currentId++);
        Storage.rooms.add(roomModel);
    }

    @Override
    public RoomModel getRoom(int id) {
        return Storage.rooms.get(getRoomIndexById(id));
    }

    @Override
    public void update(RoomModel roomModel) {
        Storage.rooms.set(getRoomIndexById(roomModel.getId()), roomModel);
    }

    @Override
    public void delete(RoomModel roomModel) {
        Storage.rooms.remove(getRoomIndexById(roomModel.getId()));
    }

    @Override
    public List<RoomModel> getAll() {
        return Storage.rooms.stream()
                .sorted(Comparator.ROOM_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        return Storage.rooms.stream()
                .sorted(Comparator.ROOM_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        return Storage.rooms.stream()
                .sorted(Comparator.ROOM_CAPACITRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        return Storage.rooms.stream()
                .sorted(Comparator.ROOM_RATING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        return Storage.rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        return Storage.rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        return Storage.rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_CAPACITRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        return Storage.rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_RATING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int getCountFreeRooms() {
                return Storage.rooms.stream()
                        .filter(x -> x.getStatus().equals(RoomStatus.FREE))
                        .collect(Collectors.toList()).size();
    }

    @Override
    public List<RoomModel> getLatestGuests(int count) {
        return Storage.rooms;
    }

    @Override
    public List<Double> getPriceBySection(RoomsSection section) {
        return null;
    }
}
