package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.RoomModelRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModelRepositoryImpl implements RoomModelRepository {

    private List<RoomModel> roomModels;
    private static int currentId=1;

    public RoomModelRepositoryImpl(List<RoomModel> roomModels) {
        this.roomModels = roomModels;
    }

    private int getGuestIndexById(int id) {
        for (int i = 0; i < this.roomModels.size(); i++) {
            if(this.roomModels.get(i).getRoomId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setRoom(RoomModel roomModel) {
        roomModel.setRoomId(currentId++);
        this.roomModels.add(roomModel);
    }

    @Override
    public RoomModel getRoom(int id) {
        return this.roomModels.get(getGuestIndexById(id));
    }

    @Override
    public void update(RoomModel roomModel) {
        this.roomModels.set(getGuestIndexById(roomModel.getRoomId()), roomModel);
    }

    @Override
    public void delete(RoomModel roomModel) {
        this.roomModels.remove(getGuestIndexById(roomModel.getRoomId()));
    }

    @Override
    public void setAll(List<RoomModel> roomModels) {
        this.roomModels.addAll(roomModels);
    }

    @Override
    public List<RoomModel> getAll() {
        return this.roomModels.stream()
                .sorted(Comparator.ROOM_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        return this.roomModels.stream()
                .sorted(Comparator.ROOM_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        return this.roomModels.stream()
                .sorted(Comparator.ROOM_CAPACITRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        return this.roomModels.stream()
                .sorted(Comparator.ROOM_RATING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        return this.roomModels.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        return this.roomModels.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        return this.roomModels.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_CAPACITRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        return this.roomModels.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_RATING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int getCountFreeRooms() {
                return this.roomModels.stream()
                        .filter(x -> x.getStatus().equals(RoomStatus.FREE))
                        .collect(Collectors.toList()).size();
    }

    @Override
    public List<RoomModel> getReleasedInFuture(LocalDate date) {
                return null;
    }

    @Override
    public List<RoomModel> getLatestGuests(int count) {
        return this.roomModels;
    }

    @Override
    public List<Integer> getPriceBySection(RoomsSection section) {
        return null;
    }
}
