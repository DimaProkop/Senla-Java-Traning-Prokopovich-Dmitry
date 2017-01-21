package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.RoomsSection;
import com.training.senla.facade.Facade;
import com.training.senla.manager.EntityManager;
import com.training.senla.manager.impl.EntityManagerImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.template.ReadTemplate;

import java.util.Date;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    public static void main(String[] args) {
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();
    }
}
