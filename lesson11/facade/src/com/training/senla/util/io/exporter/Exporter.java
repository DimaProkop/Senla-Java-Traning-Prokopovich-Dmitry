package com.training.senla.util.io.exporter;

import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface Exporter {
    void exportCollection(List guests, Class clazz);

    void exportAll(List<Guest> guests, List<Registration> registrations, List<Room> rooms, List<Service> services);
}
