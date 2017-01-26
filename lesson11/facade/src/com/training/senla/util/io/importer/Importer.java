package com.training.senla.util.io.importer;

import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface Importer {
    void importGuests(List<Guest> guests);

    void importRooms(List<Room> rooms);

    void importRegistrations(List<Registration> registrations);

    void importServices(List<Service> services);
}
