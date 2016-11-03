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

    List<Object> convertDataToObject();

    void convertDataToModel(List<Object> data);
}
