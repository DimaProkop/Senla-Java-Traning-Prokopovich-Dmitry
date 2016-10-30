package com.training.senla.service.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.service.GuestModelService;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelServiceImpl implements GuestModelService {

    private static final Logger LOG = LogManager.getLogger(GuestModelServiceImpl.class);

    private GuestModelRepository guestModelRepository;
    private RegistrationModelRepository registrationModelRepository;

    public GuestModelServiceImpl(GuestModelRepository guestModelRepository, RegistrationModelRepository registrationModelRepository) {
        this.guestModelRepository = guestModelRepository;
        this.registrationModelRepository = registrationModelRepository;
    }

    @Override
    public void setGuest(GuestModel guestModel) {
        try {
            guestModelRepository.setGuest(guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public GuestModel getGuest(int id) {
        GuestModel guest = null;
        try {
            guest = guestModelRepository.getGuest(id);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guest;
    }

    @Override
    public void update(GuestModel guestModel) {
        try {
            guestModelRepository.update(guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void delete(GuestModel guestModel) {
        try {
            guestModelRepository.delete(guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void addService(GuestModel guestModel, ServiceModel serviceModel) {
        try {
            guestModel.addService(serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void removeService(GuestModel guestModel, ServiceModel serviceModel) {
        try {
            guestModel.removeService(serviceModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public List<ServiceModel> getServicesByPrice(GuestModel guestModel) {
        List<ServiceModel> services = null;
        try {
            services = guestModelRepository.getServicesByPrice(guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<ServiceModel> getServicesByDate(GuestModel guestModel) {
        List<ServiceModel> services = null;
        try {
            services = guestModelRepository.getServicesByDate(guestModel);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return services;
    }

    @Override
    public List<GuestModel> getAll() {
        List<GuestModel> guests = null;
        try {
            guests = guestModelRepository.getAll();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guests;
    }

    @Override
    public List<GuestModel> getSortedByFinalDate() {
        List<GuestModel> guests = new ArrayList<>();
        try {
            List<RegistrationModel> registrations = registrationModelRepository.getAll().stream()
                    .sorted(Comparator.GUEST_ROOM_DATA_COMPARATOR)
                    .collect(Collectors.toList());
            List<Integer> newList = registrations.stream()
                    .map(RegistrationModel::getGuestId)
                    .collect(Collectors.toList());
            for (int i = 0; i < guestModelRepository.getAll().size(); i++) {
                GuestModel guest = guestModelRepository.getGuest(newList.get(i));
                guests.add(guest);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guests;
    }

    @Override
    public List<GuestModel> getSortedByName() {
        List<GuestModel> guests = null;
        try {
            guests = guestModelRepository.getSortedByName();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return guests;
    }

    @Override
    public double getSumByRoom(RoomModel roomModel, GuestModel guestModel) {
        double sum = 0;
        try {
            for (int i = 0; i < registrationModelRepository.getAll().size(); i++) {
                if(registrationModelRepository.getAll().get(i).getGuestId() == guestModel.getId()) {
                    int count = registrationModelRepository.getAll().get(i).getFinalDate().getDay() - registrationModelRepository.getAll().get(i).getStartDate().getDay();
                    sum = count * roomModel.getPrice();
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return sum;
    }

    @Override
    public int getCount() {
        int count = 0;
        try {
            count = guestModelRepository.getCount();
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return count;
    }
}
