package com.training.senla;

import com.danco.training.TextFileWorker;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
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

        ServiceModel serviceModel = new ServiceModel("Shower", 54.34, ServicesSection.MANDATORY, LocalDate.now(), LocalDate.of(2017, 6, 6));
        facade.setService(serviceModel);

        RoomModel roomModel = new RoomModel(200.5, 3, RoomsSection.LUKS, 5);

        RoomModel roomModel1 = new RoomModel(63.1, 2, RoomsSection.STANDART, 3);

        facade.setRoom(roomModel);
        facade.setRoom(roomModel1);

        facade.exportAll();


        for(RoomModel room: facade.getAllRooms()) {
            System.out.println(room.getStatus());
            System.out.println(room.getPrice());
        }

    }
}
