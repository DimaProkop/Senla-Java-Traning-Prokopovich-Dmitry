package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelRepositoryImpl implements GuestModelRepository {

    private List<GuestModel> guestModels;
    public static int currentId=1;

    public GuestModelRepositoryImpl(List<GuestModel> guestModels) {
        this.guestModels = guestModels;
    }

    private int getGuestIndexById(int id) {
        for (int i = 0; i < this.guestModels.size(); i++) {
            if(this.guestModels.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setGuest(GuestModel guestModel) {
        guestModel.setId(currentId++);
        this.guestModels.add(guestModel);
    }

    @Override
    public GuestModel getGuest(int id) {
        return this.guestModels.get(getGuestIndexById(id));
    }

    @Override
    public void update(GuestModel guestModel) {
        this.guestModels.set(getGuestIndexById(guestModel.getId()), guestModel);
    }

    @Override
    public void delete(GuestModel guestMode) {
        this.guestModels.remove(getGuestIndexById(guestMode.getId()));
    }

    @Override
    public List<ServiceModel> getServicesByDate(GuestModel guestModel) {
        return this.guestModels.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getServicesByPrice(GuestModel guestModel) {
        return this.guestModels.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getAll() {
        return this.guestModels.stream()
                .sorted(Comparator.GUEST_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getSortedByName() {
        return this.guestModels.stream()
                .sorted(Comparator.GUEST_NAME_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int getCount() {
        return this.guestModels.size();
    }
}
