package com.training.senla.util.io.exporter;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface Exporter {
    void exportCollection(List guests, Class clazz);

    void exportAll(List<GuestModel> guests, List<RegistrationModel> registrations, List<RoomModel> rooms, List<ServiceModel> services);
}
