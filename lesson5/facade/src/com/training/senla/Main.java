package com.training.senla;

import com.danco.training.TextFileWorker;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson5/facade/resource/main.txt";
    public static void main(String[] args) throws IOException {
        TextFileWorker textFileWorker = new TextFileWorker(FILE_PATH);
        Facade facade = new FacadeImpl();
        facade.init(textFileWorker);

        ServiceModel serviceModel = new ServiceModel("smth", 23.3, ServicesSection.MANDATORY, LocalDate.now(), LocalDate.of(2020, 5, 5));
        GuestModel guestModel = new GuestModel("Dima");
        guestModel.removeService(serviceModel);


        for(RoomModel room: facade.getAllRooms()) {
            System.out.println(room.getStatus());
            System.out.println(room.getPrice());
        }

    }
}
