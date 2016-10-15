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
    public List<RoomModel> getAll() {
        Collections.sort(this.roomModels, Comparator.ROOM_ID_COMPARATOR);
        return this.roomModels;
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        Collections.sort(this.roomModels, Comparator.ROOM_PRICE_COMPARATOR);
        return this.roomModels;
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        Collections.sort(this.roomModels, Comparator.ROOM_CAPACITRY_COMPARATOR);
        return this.roomModels;
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        Collections.sort(this.roomModels, Comparator.ROOM_RATING_COMPARATOR);
        return this.roomModels;
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        Collections.sort(this.roomModels.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList()), Comparator.ROOM_ID_COMPARATOR);
        return this.roomModels;
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        Collections.sort(this.roomModels.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList()), Comparator.ROOM_PRICE_COMPARATOR);
        return this.roomModels;
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        Collections.sort(this.roomModels.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList()), Comparator.ROOM_CAPACITRY_COMPARATOR);
        return this.roomModels;
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        Collections.sort(this.roomModels.stream()
                .filter(x -> x.getStatus().equals(status))
                .collect(Collectors.toList()), Comparator.ROOM_RATING_COMPARATOR);
        return this.roomModels;
    }

    @Override
    public int getCountFreeRooms() {
        Collections.sort(this.roomModels.stream()
                .filter(x -> x.getStatus().equals(RoomStatus.FREE))
                .collect(Collectors.toList()), Comparator.ROOM_RATING_COMPARATOR);
        return this.roomModels.size();
    }

    @Override
    public List<RoomModel> getReleasedInFuture(LocalDate date) {
        return this.roomModels.stream()
                .filter(x->x.getFinalDate().getDayOfYear()<date.getDayOfYear())
                .collect(Collectors.toList());
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
