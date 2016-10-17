package com.training.senla.facade;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

/**
 * Created by prokop on 13.10.16.
 */
public interface Facade {
    GuestModel getGuest(int id);
    RoomModel getRoom(int id);
    RegistrationModel getRegistration(int id);
    ServiceModel getService(int id);
}
