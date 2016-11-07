package com.training.senla;

import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    public static void main(String[] args) throws IOException {
        FacadeImpl.getInstance().init();
    }
}
