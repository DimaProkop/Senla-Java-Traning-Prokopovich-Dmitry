package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.RoomsSection;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;

import java.io.IOException;
import java.util.Date;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    public static void main(String[] args) throws IOException {
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();

        GuestModel g = facade.getGuest(1);
        RoomModel r = facade.getRoom(1);
        Date s = new Date(5,5,2016);
        Date f = new Date(6,6,2018);

        facade.registerGuest(g, r, s, f);
        int k = 1 +1 ;
    }
}
