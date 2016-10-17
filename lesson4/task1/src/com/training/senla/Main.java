package com.training.senla;

import com.danco.training.TextFileWorker;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.io.importer.impl.ImporterImpl;

import java.io.IOException;
import java.util.List;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson4/task1/resource/main.txt";
    public static TextFileWorker textFileWorker = new TextFileWorker(FILE_PATH);

    public static void main(String[] args) throws IOException {

        //Facade facade = new FacadeImpl(new GuestModelServiceImpl(new GuestModelRepositoryImpl(new ArrayList<>())), new RoomModelServiceImpl(new RoomModelRepositoryImpl(new ArrayList<>()), new GuestModelRepositoryImpl(new ArrayList<>()), new RegistrationModelRepositoryImpl(new ArrayList<>())), new RegistrationModelServiceImpl(new RegistrationModelRepositoryImpl(new ArrayList<>())), new ServiceModelServiceImpl(new ServiceModelRepositoryImpl(new ArrayList<>())));
        Facade facade = new FacadeImpl();
//        ServiceModel serviceModel = new ServiceModel("shower", 8.1, ServicesSection.MANDATORY, LocalDate.now(), LocalDate.of(2017, 6, 6));
//        ServiceModel serviceModel1 = new ServiceModel("food", 23.5, ServicesSection.FOOD, LocalDate.now(), LocalDate.of(2018, 11, 15));
//        List<ServiceModel> serviceModels = new ArrayList<>();
//        serviceModels.add(serviceModel);
//        serviceModels.add(serviceModel1);
//        ServiceModelService serviceModelService = new ServiceModelServiceImpl(new ServiceModelRepositoryImpl(serviceModels));
//        serviceModelService.setService(serviceModel);
//        serviceModelService.setService(serviceModel1);
//        Facade facade = new FacadeImpl();
//        String[] textvalues = new String[serviceModels.size()];
//
//        for (int i = 0; i < serviceModels.size(); i++) {
//            textvalues[i] = ServiceToString(serviceModels.get(i));
//        }
//
//        textFileWorker.writeToFile(textvalues);

        Importer importer = new ImporterImpl(facade);

        List<ServiceModel> serviceModels = importer.importServices();

        for(ServiceModel serviceModel : serviceModels) {
            System.out.println(serviceModel.getName());
        }



    }


//        LocalDate today = LocalDate.now();
//        LocalDate f = LocalDate.of(2021, 6, 6);
//        LocalDate future = LocalDate.of(2016, Month.OCTOBER, 20);
//        LocalDate future1 = LocalDate.of(2019, Month.AUGUST, 6);
//        LocalDate future2 = LocalDate.of(2025, Month.AUGUST, 6);
//        RoomModel roomModel = new RoomModel(52.5, 4, RoomsSection.STANDART, 3, new ArrayList<>());
//        ServiceModel serviceModel = new ServiceModel("shower", 2.5, ServicesSection.MANDATORY, today, future);
//        List<ServiceModel> serviceModelList = new ArrayList<>();
//        serviceModelList.add(serviceModel);
//
//        List<RoomModel> roomModels = new ArrayList<>();
//        List<RegistrationModel> registrationModels = new ArrayList<>();
//        //GUEST
//        GuestModel guestModel = new GuestModel("Dima", today, serviceModelList);
//        guestModel.setFinalDate(future);
//        GuestModel guestModel1 = new GuestModel("Asdfasdf", today, serviceModelList);
//        GuestModel guestModel2 = new GuestModel("123123", today, serviceModelList);
//        GuestModel guestModel3 = new GuestModel("____!_!_!_", today, serviceModelList);
//        List<GuestModel> guests = new ArrayList<>();
//
//        GuestModelService guestModelService = new GuestModelServiceImpl( new GuestModelRepositoryImpl(guests));
//        guestModelService.setGuest(guestModel);
//        guestModelService.setGuest(guestModel1);
//
//        roomModel.setStatus(RoomStatus.MAINTAINED);
//
//        RoomModelService roomModelService = new RoomModelServiceImpl( new RoomModelRepositoryImpl(roomModels), new GuestModelRepositoryImpl(guests), new RegistrationModelRepositoryImpl(registrationModels));
//        roomModelService.setRoom(roomModel);
//        roomModelService.addGuest(guestModel, roomModel);
//        roomModelService.addGuest(guestModel1, roomModel);
//
//        System.out.println(guestModelService.getSumByRoom(roomModel, guestModel));
//
//        roomModelService.evictGuest(guestModel);

}
