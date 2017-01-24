package com.training.senla.service;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.dao.GuestModelDao;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface GuestModelService {
    void addGuest(GuestModel guestModel);

    GuestModel getGuest(int id);

    void update(GuestModel guestModel);

    void delete(GuestModel guestModel);

    void addService(GuestModel guestModel, ServiceModel serviceModel);

    void removeService(GuestModel guestModel, ServiceModel serviceModel);

    List<ServiceModel> getServicesByPrice(GuestModel guestModel);

    List<ServiceModel> getServicesByDate(GuestModel guestModel);

    List<GuestModel> getAll();

    List<GuestModel> getSortedByFinalDate();

    List<GuestModel> getSortedByName();

    double getSumByRoom(RoomModel roomModel, GuestModel guestModel);

    int getCount();

    void setGuestModelRepository(GuestModelDao guestModelRepository);
}
