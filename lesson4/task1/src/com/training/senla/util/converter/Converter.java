package com.training.senla.util.converter;

import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

/**
 * Created by prokop on 18.10.16.
 */
public interface Converter {
    String convertGuestToString(GuestModel guestModel);

    String convertRoomToString(RoomModel roomModel);

    String convertServiceToString(ServiceModel serviceModel);

    String convertRegistrationToString(RegistrationModel registrationModel);

    GuestModel convertStringToGuest(String string, Facade facade);

    RoomModel convertStringToRoom(String string, Facade facade);

    ServiceModel convertStringToService(String string);

    RegistrationModel convertStringToRegistration(String string);
}
