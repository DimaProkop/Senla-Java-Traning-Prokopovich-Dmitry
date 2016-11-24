package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.model.GuestModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelRepositoryImpl implements GuestModelRepository {

    private int currentId=1;
    private List<GuestModel> guests;

    public void calcCurrentId() {
        int maxId = 0;
        if(guests == null){
            currentId = 1;
        }else {
            for (GuestModel guest : guests) {
                if (guest.getId() > maxId) {
                    maxId = guest.getId();
                }
            }
            currentId = maxId + 1;
        }
    }

    public GuestModelRepositoryImpl() {
        calcCurrentId();
    }

    private int getGuestIndexById(int id) {
        for (int i = 0; i < guests.size(); i++) {
            if(guests.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void setGuest(GuestModel guestModel) {
        guestModel.setId(currentId++);
        guests.add(guestModel);
    }

    @Override
    public GuestModel getGuest(int id) {
        GuestModel guest = null;
        if(id != -1) {
            guest = guests.get(getGuestIndexById(id));
        }
        return guest;
    }

    @Override
    public void update(GuestModel guestModel) {
        guests.set(getGuestIndexById(guestModel.getId()), guestModel);
    }

    @Override
    public void delete(GuestModel guestMode) {
        guests.remove(getGuestIndexById(guestMode.getId()));
    }

    @Override
    public List<ServiceModel> getServicesByDate(GuestModel guestModel) {
        return guests.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getServicesByPrice(GuestModel guestModel) {
        return guests.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getAll() {
        return guests.stream()
                .sorted(Comparator.GUEST_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getSortedByName() {
        return guests.stream()
                .sorted(Comparator.GUEST_NAME_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int getCount() {
        return guests.size();
    }

    @Override
    public void setGuests(List<GuestModel> guests) {
        this.guests = guests;
    }
}
