package com.training.senla;

import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.io.IOException;
import java.util.Date;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    public static void main(String[] args) throws IOException {
        FacadeImpl.getInstance().init();

        GuestModel guestModel = new GuestModel("Dimka");
        FacadeImpl.getInstance().addGuest(guestModel);
        RoomModel roomModel = new RoomModel(22, 3, RoomsSection.LUKS, 4);
        RoomModel room2 = new RoomModel(1111, 4, RoomsSection.IMPROVED, 5);
        FacadeImpl.getInstance().addRoom(roomModel);
        FacadeImpl.getInstance().addRoom(room2);
        FacadeImpl.getInstance().registerGuest(guestModel, roomModel, new Date(2016, 6, 6), new Date(2017, 5, 5));

        ServiceModel serviceModel = new ServiceModel("shower", 43, ServicesSection.MANDATORY, new Date(2016, 6, 6), new Date(2017, 5, 5));
        FacadeImpl.getInstance().addService(serviceModel);

        FacadeImpl.getInstance().exportAll();


    }
}
