package com.training.senla.util.converter;

import com.training.senla.model.*;
import com.training.senla.template.ReadTemplate;

/**
 * Created by prokop on 18.10.16.
 */
public interface Converter {
    Guest convertStringToGuest(String string, ReadTemplate template);

    Room convertStringToRoom(String string, ReadTemplate template);

    Service convertStringToService(String string, ReadTemplate template);

    Registration convertStringToRegistration(String string, ReadTemplate template);
}
