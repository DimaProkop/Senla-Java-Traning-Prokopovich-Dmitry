package com.training.senla.repository.impl;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.RegistrationModel;
import com.training.senla.repository.RegistrationModelRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationModelRepositoryImpl implements RegistrationModelRepository {

    private List<RegistrationModel> registrations;
    private int currentId=1;

    public void calcCurrentId() {
        int maxId = 0;
        if(registrations == null) {
            currentId = 1;
        }else {
            for (RegistrationModel registration : registrations) {
                if (registration.getId() > maxId) {
                    maxId = registration.getId();
                }
            }
            currentId = maxId + 1;
        }
    }

    private int getRegistrationIndexById(int id) {
        for (int i = 0; i < registrations.size(); i++) {
            if(registrations.get(i).getGuestId() == id) {
                return i;
            }
        }
        return -1;
    }

    public RegistrationModelRepositoryImpl(List<RegistrationModel> registrationModelList) {
        registrations = registrationModelList;
        calcCurrentId();
    }

    @Override
    public void addRecord(RegistrationModel registrationModel) {
        registrationModel.setId(currentId++);
        List<Integer> oldIds = registrations.stream()
                .map(x->x.getRoomId())
                .distinct()
                .collect(Collectors.toList());
        int countRecords = Integer.parseInt(FacadeImpl.getInstance().getProperty("count.registrations"));
        if(countRecords<oldIds.size()) {
            registrations.remove(registrations.get(0));
            registrations.add(registrationModel);
        }else {
            registrations.add(registrationModel);
        }
    }

    @Override
    public void update(RegistrationModel registrationModel) {
        registrations.set(registrationModel.getId(), registrationModel);
    }

    @Override
    public RegistrationModel getRegistration(int id) {
        return registrations.get(getRegistrationIndexById(id));
    }

    @Override
    public List<RegistrationModel> getAll() {
        return registrations;
    }
}
