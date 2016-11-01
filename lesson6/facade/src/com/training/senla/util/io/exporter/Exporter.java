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
    void exportGuests(List<GuestModel> guests);

    void exportRegistrations(List<RegistrationModel> registrations);

    void exportRooms(List<RoomModel> rooms);

    void exportServices(List<ServiceModel> services);

    void exportAll(List<ServiceModel> services, List<RoomModel> rooms, List<GuestModel> guests, List<RegistrationModel> registrations);
}
