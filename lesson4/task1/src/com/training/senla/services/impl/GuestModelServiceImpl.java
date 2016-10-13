package com.training.senla.services.impl;

import com.training.senla.models.GuestModel;
import com.training.senla.models.RoomModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.services.GuestModelService;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelServiceImpl implements GuestModelService {

    private GuestModelRepository guestModelRepository;

    @Override
    public void setGuest(GuestModel guestModel) {

    }

    @Override
    public GuestModel getGuest(int id) {
        return null;
    }

    @Override
    public void update(GuestModel guestModel) {

    }

    @Override
    public void delete(GuestModel guestModel) {

    }

    @Override
    public List<GuestModel> getAll() {
        return null;
    }

    @Override
    public List<GuestModel> getSortedByFinalDate() {
        return null;
    }

    @Override
    public List<GuestModel> getSortedByName() {
        return null;
    }

    @Override
    public int getSumByRoom(RoomModel roomModel) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
