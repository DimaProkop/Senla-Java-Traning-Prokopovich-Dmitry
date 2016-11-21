package com.training.senla.util.converter;

import com.training.senla.model.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by prokop on 18.10.16.
 */
public interface Converter {
    GuestModel convertStringToGuest(String string, ReadTemplate template);

    RoomModel convertStringToRoom(String string, ReadTemplate template);

    ServiceModel convertStringToService(String string, ReadTemplate template);

    RegistrationModel convertStringToRegistration(String string, ReadTemplate template);
}
