package com.training.senla.util.io.importer;

/**
 * Created by prokop on 16.10.16.
 */
public interface Importer {
    void importGuests();

    void importRegistrations();

    void importRooms();

    void importServices();

    void importAll();
}
