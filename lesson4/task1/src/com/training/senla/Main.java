package com.training.senla;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.repository.impl.GuestModelRepositoryImpl;
import com.training.senla.service.GuestModelService;
import com.training.senla.service.ServiceModelService;
import com.training.senla.service.impl.GuestModelServiceImpl;
import com.training.senla.service.impl.ServiceModelServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 12.10.16.
 */
public class Main {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate future = LocalDate.of(2016, Month.OCTOBER, 20);
        LocalDate future1 = LocalDate.of(2019, Month.AUGUST, 6);
        LocalDate future2 = LocalDate.of(2025, Month.AUGUST, 6);
        RoomModel roomModel = new RoomModel(0, 52.5, 4, RoomStatus.FREE, RoomsSection.STANDART, 3, today, future, new ArrayList<>());
        ServiceModel serviceModel = new ServiceModel(0, "showe r", 2.5, ServicesSection.MANDATORY, today, future);
        List<ServiceModel> serviceModelList = new ArrayList<>();
        serviceModelList.add(serviceModel);
        GuestModel guestModel = new GuestModel(0, "Dima", today, future2, roomModel, serviceModelList);
        GuestModel guestModel1 = new GuestModel(1, "Asdfasdf", today, future, roomModel, serviceModelList);
        GuestModel guestModel2 = new GuestModel(2, "123123", today, future1, roomModel, serviceModelList);
        GuestModel guestModel3 = new GuestModel(3, "____!_!_!_", today, future2, roomModel, serviceModelList);
        List<GuestModel> guests = new ArrayList<>();

        guests.add(guestModel1);
        guests.add(guestModel);
        guests.add(guestModel3);
        guests.add(guestModel2);
        GuestModelService guestModelService = new GuestModelServiceImpl(new GuestModelRepositoryImpl(guests));

        System.out.println(future.getDayOfYear() - today.getDayOfYear());

        for (int i = 0; i < guestModelService.getSortedByFinalDate().size(); i++) {
            guestModel = guests.get(i);
            System.out.println(guestModel.getGuestId());
        }

    }
}
