package com.training.senla.services.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.models.RoomModel;
import com.training.senla.repository.RoomModelRepository;
import com.training.senla.services.RoomModelService;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModelServiceImpl implements RoomModelService {

    private RoomModelRepository roomModelRepository;

    @Override
    public void setRoom(RoomModel roomModel) {

    }

    @Override
    public RoomModel getRoom(int id) {
        return null;
    }

    @Override
    public void update(RoomModel roomModel) {

    }

    @Override
    public void delete(RoomModel roomModel) {

    }

    @Override
    public List<RoomModel> getAll() {
        return null;
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        return null;
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        return null;
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        return null;
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        return null;
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        return null;
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        return null;
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        return null;
    }

    @Override
    public int getCountFreeRooms() {
        return 0;
    }

    @Override
    public List<RoomModel> getReleasedInFuture(Date date) {
        return null;
    }

    @Override
    public List<RoomModel> getLatestGuests(int count) {
        return null;
    }

    @Override
    public List<Integer> getPriceBySection(RoomsSection section) {
        return null;
    }
}
