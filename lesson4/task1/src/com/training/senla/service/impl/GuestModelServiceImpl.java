package com.training.senla.service.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.service.GuestModelService;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelServiceImpl implements GuestModelService {

    private GuestModelRepository guestModelRepository;

    public GuestModelServiceImpl(GuestModelRepository guestModelRepository) {
        this.guestModelRepository = guestModelRepository;
    }

    @Override
    public void setGuest(GuestModel guestModel) {
        guestModelRepository.setGuest(guestModel);
    }

    @Override
    public GuestModel getGuest(int id) {
        return guestModelRepository.getGuest(id);
    }

    @Override
    public void update(GuestModel guestModel) {
        guestModelRepository.update(guestModel);
    }

    @Override
    public void delete(GuestModel guestModel) {
        guestModelRepository.delete(guestModel);
    }

    @Override
    public void setAll(List<GuestModel> guestModels) {
        guestModelRepository.setAll(guestModels);
    }

    @Override
    public List<GuestModel> getAll() {
        return guestModelRepository.getAll();
    }

    @Override
    public List<GuestModel> getSortedByFinalDate() {
        return guestModelRepository.getSortedByFinalDate();
    }

    @Override
    public List<GuestModel> getSortedByName() {
        return guestModelRepository.getSortedByName();
    }

    @Override
    public double getSumByRoom(RoomModel roomModel, GuestModel guestModel) {
        return guestModelRepository.getSumByRoom(roomModel, guestModel);
    }

    @Override
    public int getCount() {
        return guestModelRepository.getCount();
    }
}
