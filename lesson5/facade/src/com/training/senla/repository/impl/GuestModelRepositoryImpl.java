package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.storage.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelRepositoryImpl implements GuestModelRepository {

    public static int currentId=1;

    public GuestModelRepositoryImpl(List<GuestModel> guestModels) {
         Storage.guests = guestModels;
    }

    private int getGuestIndexById(int id) {
        for (int i = 0; i < Storage.guests.size(); i++) {
            if(Storage.guests.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setGuest(GuestModel guestModel) {
        guestModel.setId(currentId++);
        Storage.guests.add(guestModel);
    }

    @Override
    public GuestModel getGuest(int id) {
        return Storage.guests.get(getGuestIndexById(id));
    }

    @Override
    public void update(GuestModel guestModel) {
        Storage.guests.set(getGuestIndexById(guestModel.getId()), guestModel);
    }

    @Override
    public void delete(GuestModel guestMode) {
        Storage.guests.remove(getGuestIndexById(guestMode.getId()));
    }

    @Override
    public List<ServiceModel> getServicesByDate(GuestModel guestModel) {
        return Storage.guests.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getServicesByPrice(GuestModel guestModel) {
        return Storage.guests.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getAll() {
        return Storage.guests.stream()
                .sorted(Comparator.GUEST_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getSortedByName() {
        return Storage.guests.stream()
                .sorted(Comparator.GUEST_NAME_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int getCount() {
        return Storage.guests.size();
    }
}
