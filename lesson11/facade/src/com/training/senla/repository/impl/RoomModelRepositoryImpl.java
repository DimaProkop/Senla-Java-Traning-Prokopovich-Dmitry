package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.RoomModelRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModelRepositoryImpl implements RoomModelRepository {

    private List<RoomModel> rooms;
    private int currentId=1;

    @Override
    public void calcCurrentId() {
        int maxId = 0;
        if(rooms == null) {
            currentId = 1;
        }else {
            for (RoomModel room : rooms) {
                if (room.getId() > maxId) {
                    maxId = room.getId();
                }
            }
            currentId = maxId + 1;
        }
    }

    public RoomModelRepositoryImpl() {
        calcCurrentId();
    }

    private int getRoomIndexById(int id) {
        for (int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void addRoom(RoomModel roomModel) {
        roomModel.setId(currentId++);
        rooms.add(roomModel);
    }

    @Override
    public RoomModel getRoom(int id) {
        RoomModel room = null;
        if(id != -1) {
            room = rooms.get(getRoomIndexById(id));
        }
        return room;
    }

    @Override
    public void update(RoomModel roomModel) {
        rooms.set(getRoomIndexById(roomModel.getId()), roomModel);
    }

    @Override
    public void delete(RoomModel roomModel) {
        rooms.remove(getRoomIndexById(roomModel.getId()));
    }

    @Override
    public List<RoomModel> getAll() {
        return rooms.stream()
                .sorted(Comparator.ROOM_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        return rooms.stream()
                .sorted(Comparator.ROOM_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        return rooms.stream()
                .sorted(Comparator.ROOM_CAPACITRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        return rooms.stream()
                .sorted(Comparator.ROOM_RATING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        return rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        return rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        return rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_CAPACITRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        return rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_RATING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int getCountFreeRooms() {
                return rooms.stream()
                        .filter(x -> x.getStatus().equals(RoomStatus.FREE))
                        .collect(Collectors.toList()).size();
    }

    @Override
    public List<RoomModel> getLatestGuests(int count) {
        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(RoomsSection section) {
        return null;
    }

    @Override
    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }
}
