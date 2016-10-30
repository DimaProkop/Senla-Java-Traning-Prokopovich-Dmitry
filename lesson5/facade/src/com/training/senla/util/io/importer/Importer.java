package com.training.senla.util.io.importer;

import com.danco.training.TextFileWorker;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public interface Importer {
    List<GuestModel> importGuests();

    List<RegistrationModel> importRegistrations();

    List<RoomModel> importRooms();

    List<ServiceModel> importServices();

    void loadData(String path);
}
