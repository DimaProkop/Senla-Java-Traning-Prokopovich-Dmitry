package com.training.senla.util.io.importer;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface Importer {
    void importGuests(List<GuestModel> guests);

    void importRooms(List<RoomModel> rooms);

    void importRegistrations(List<RegistrationModel> registrations);

    void importServices(List<ServiceModel> services);

    void setServices(List<ServiceModel> services);

    void setRooms(List<RoomModel> rooms);
}
