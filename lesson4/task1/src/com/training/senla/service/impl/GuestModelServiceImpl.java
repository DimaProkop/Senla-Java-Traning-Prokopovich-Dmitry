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

    public static Integer currentId;

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
    public double getSumByRoom(RoomModel roomModel) {
        return guestModelRepository.getSumByRoom(roomModel);
    }

    @Override
    public int getCount() {
        return guestModelRepository.getCount();
    }
}
