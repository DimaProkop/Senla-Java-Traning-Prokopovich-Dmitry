package com.training.senla.util.initializer;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.impl.GuestModelRepositoryImpl;
import com.training.senla.service.GuestModelService;
import com.training.senla.service.RegistrationModelService;
import com.training.senla.service.RoomModelService;
import com.training.senla.service.ServiceModelService;
import com.training.senla.service.impl.GuestModelServiceImpl;

import java.util.List;

/**
 * Created by prokop on 15.10.16.
 */
public class Initializer {
    private List<GuestModel> guestModels;
    private List<RoomModel> roomModels;
    private List<RegistrationModel> registrationModels;
    private List<ServiceModel> serviceModels;

    private GuestModelService guestModelService;
    private RoomModelService roomModelService;
    private RegistrationModelService registrationModelService;
    private ServiceModelService serviceModelService;


    public void fillServices() {
        guestModelService = new GuestModelServiceImpl(new GuestModelRepositoryImpl(guestModels));
    }
}
