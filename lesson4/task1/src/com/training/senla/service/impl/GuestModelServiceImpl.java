package com.training.senla.service.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.service.GuestModelService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelServiceImpl implements GuestModelService {

    private GuestModelRepository guestModelRepository;
    private RegistrationModelRepository registrationModelRepository;

    public GuestModelServiceImpl(GuestModelRepository guestModelRepository, RegistrationModelRepository registrationModelRepository) {
        this.guestModelRepository = guestModelRepository;
        this.registrationModelRepository = registrationModelRepository;
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
    public void addService(GuestModel guestModel, ServiceModel serviceModel) {
        guestModel.addService(serviceModel);
    }

    @Override
    public void removeService(GuestModel guestModel, ServiceModel serviceModel) {
        guestModel.removeService(serviceModel);
    }

    @Override
    public List<ServiceModel> getServicesByPrice(GuestModel guestModel) {
        return guestModelRepository.getServicesByPrice(guestModel);
    }

    @Override
    public List<ServiceModel> getServicesByDate(GuestModel guestModel) {
        return guestModelRepository.getServicesByDate(guestModel);
    }

    @Override
    public List<GuestModel> getAll() {
        return guestModelRepository.getAll();
    }

    @Override
    public List<GuestModel> getSortedByFinalDate() {
        List<Integer> newList = registrationModelRepository.getAll().stream()
                .map(RegistrationModel::getGuestId)
                .collect(Collectors.toList());

        return null;
    }

    @Override
    public List<GuestModel> getSortedByName() {
        return guestModelRepository.getSortedByName();
    }

    @Override
    public double getSumByRoom(RoomModel roomModel, GuestModel guestModel) {
        return 0;
    }

    @Override
    public int getCount() {
        return guestModelRepository.getCount();
    }
}
