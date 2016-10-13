package com.training.senla.repository.impl;

import com.training.senla.models.GuestModel;
import com.training.senla.models.RoomModel;
import com.training.senla.repository.GuestModelRepository;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelRepositoryImpl implements GuestModelRepository {

    private List<GuestModel> guestModels;

    public GuestModelRepositoryImpl(List<GuestModel> guestModels) {
        this.guestModels = guestModels;
    }

    private int getGuestIndexById(int id) {
        for (int i = 0; i < this.guestModels.size(); i++) {
            if(this.guestModels.get(i).getGuestId() == id) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void setGuest(GuestModel guestModel) {
        this.guestModels.add(guestModel);
    }

    @Override
    public GuestModel getGuest(int id) {
        return this.guestModels.get(getGuestIndexById(id));
    }

    @Override
    public void update(GuestModel guestModel) {
        this.guestModels.set(getGuestIndexById(guestModel.getGuestId()), guestModel);
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
