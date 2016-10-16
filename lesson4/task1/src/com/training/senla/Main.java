package com.training.senla;

import com.danco.training.TextFileWorker;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.impl.GuestModelRepositoryImpl;
import com.training.senla.repository.impl.RegistrationModelRepositoryImpl;
import com.training.senla.repository.impl.RoomModelRepositoryImpl;
import com.training.senla.repository.impl.ServiceModelRepositoryImpl;
import com.training.senla.service.GuestModelService;
import com.training.senla.service.RoomModelService;
import com.training.senla.service.impl.GuestModelServiceImpl;
import com.training.senla.service.impl.RoomModelServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    private static String ConvertToStringGuests(GuestModel guestModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(guestModel.getGuestId()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getName()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getStartDate()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getFinalDate()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getRoomModel().getRoomId()));
        builder.append(";");
        return builder.toString();
    }

    private static String ConvertToStringRooms(RoomModel roomModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(roomModel.getRoomId()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getPrice()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getCapacity()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getRating()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getSection()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getStatus()));
        builder.append(";");
        return builder.toString();
    }


    public static void main(String[] args) throws IOException {


        LocalDate today = LocalDate.now();
        LocalDate future = LocalDate.of(2016, Month.OCTOBER, 20);
        LocalDate future1 = LocalDate.of(2019, Month.AUGUST, 6);
        LocalDate future2 = LocalDate.of(2025, Month.AUGUST, 6);
        RoomModel roomModel = new RoomModel(52.5, 4, RoomsSection.STANDART, 3, new ArrayList<>());
        ServiceModel serviceModel = new ServiceModel("shower", 2.5, ServicesSection.MANDATORY, today, future);
        List<ServiceModel> serviceModelList = new ArrayList<>();
        serviceModelList.add(serviceModel);

        List<RoomModel> roomModels = new ArrayList<>();
        List<RegistrationModel> registrationModels = new ArrayList<>();
        //GUEST
        GuestModel guestModel = new GuestModel("Dima", today, serviceModelList);
        guestModel.setFinalDate(future);
        GuestModel guestModel1 = new GuestModel("Asdfasdf", today, serviceModelList);
        GuestModel guestModel2 = new GuestModel("123123", today, serviceModelList);
        GuestModel guestModel3 = new GuestModel("____!_!_!_", today, serviceModelList);
        List<GuestModel> guests = new ArrayList<>();

        GuestModelService guestModelService = new GuestModelServiceImpl( new GuestModelRepositoryImpl(guests));
        guestModelService.setGuest(guestModel);

        RoomModelService roomModelService = new RoomModelServiceImpl( new RoomModelRepositoryImpl(roomModels), new GuestModelRepositoryImpl(guests), new RegistrationModelRepositoryImpl(registrationModels));
        roomModelService.setRoom(roomModel);
        roomModelService.addGuest(guestModel, roomModel);

        System.out.println(guestModelService.getSumByRoom(roomModel, guestModel));


    }
}
