package com.training.senla.storage;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 31.10.16.
 */
public class Storage {
    public static List<GuestModel> guests = new ArrayList<>();
    public static List<RoomModel> rooms = new ArrayList<>();
    public static List<RegistrationModel>  registrations = new ArrayList<>();
    public static List<ServiceModel> services = new ArrayList<>();
}
