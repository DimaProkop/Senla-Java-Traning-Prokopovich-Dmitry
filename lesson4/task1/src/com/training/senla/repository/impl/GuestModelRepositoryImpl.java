package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.GuestModelRepository;

import java.util.Collections;
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
        return -1;
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
    public void delete(GuestModel guestMode) {
        this.guestModels.remove(getGuestIndexById(guestMode.getGuestId()));
    }

    @Override
    public List<GuestModel> getAll() {
        Collections.sort(this.guestModels, Comparator.GUEST_ID_COMPARATOR);
        return this.guestModels;
    }

    @Override
    public List<GuestModel> getSortedByFinalDate() {
        Collections.sort(this.guestModels, Comparator.GUEST_ROOM_DATA_COMPARATOR);
        return this.guestModels;
    }

    @Override
    public List<GuestModel> getSortedByName() {
        Collections.sort(this.guestModels, Comparator.GUEST_NAME_COMPARATOR);
        return this.guestModels;
    }

    @Override
    public double getSumByRoom(RoomModel roomModel) {
        int timeDiff = Math.abs(roomModel.getFinalDate().getDayOfYear()- roomModel.getStartDate().getDayOfYear());
        return timeDiff * roomModel.getPrice();
    }

    @Override
    public int getCount() {
        return this.guestModels.size();
    }
}
