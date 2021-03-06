package com.training.senla.util.converter;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by prokop on 18.10.16.
 */
public interface Converter {

    String convertGuestToString(GuestModel guestModel);

    String convertRoomToString(RoomModel roomModel);

    String convertServiceToString(ServiceModel serviceModel);

    String convertRegistrationToString(RegistrationModel registrationModel);

    GuestModel convertStringToGuest(String string, Map<Integer, RoomModel> roomsMap, Map<Integer, ServiceModel> servicesMap);

    RoomModel convertStringToRoom(String string);

    ServiceModel convertStringToService(String string);

    RegistrationModel convertStringToRegistration(String string);
}
