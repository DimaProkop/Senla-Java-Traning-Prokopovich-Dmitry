package com.training.senla.service.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.RoomModelRepository;
import com.training.senla.service.RoomModelService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModelServiceImpl implements RoomModelService {

    private RoomModelRepository roomModelRepository;

    public RoomModelServiceImpl(RoomModelRepository roomModelRepository) {
        this.roomModelRepository = roomModelRepository;
    }

    @Override
    public void setRoom(RoomModel roomModel) {
        roomModelRepository.setRoom(roomModel);
    }

    @Override
    public RoomModel getRoom(int id) {
        return roomModelRepository.getRoom(id);
    }

    @Override
    public void update(RoomModel roomModel) {
        roomModelRepository.update(roomModel);
    }

    @Override
    public void delete(RoomModel roomModel) {
        roomModelRepository.delete(roomModel);
    }

    @Override
    public List<RoomModel> getAll() {
        return roomModelRepository.getAll();
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        return roomModelRepository.getSortedByPrice();
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        return roomModelRepository.getSortedByCapacity();
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        return roomModelRepository.getSortedByRating();
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        return roomModelRepository.getAll(status);
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        return roomModelRepository.getSortedByPrice(status);
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        return getSortedByCapacity(status);
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        return getSortedByRating(status);
    }

    @Override
    public int getCountFreeRooms() {
        return roomModelRepository.getCountFreeRooms();
    }

    @Override
    public List<RoomModel> getReleasedInFuture(LocalDate date) {
        return getReleasedInFuture(date);
    }

    @Override
    public List<RoomModel> getLatestGuests(int count) {
        return roomModelRepository.getLatestGuests(count);
    }

    @Override
    public List<Double> getPriceBySection(RoomsSection section) {
        return getPriceBySection(section);
    }
}
