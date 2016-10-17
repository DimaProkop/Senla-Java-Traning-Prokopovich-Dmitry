package com.training.senla.util.io.importer.impl;

import com.danco.training.TextFileWorker;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.io.importer.Importer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ImporterImpl implements Importer {

    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson4/task1/resource/main.txt";
    private TextFileWorker textFileWorker = new TextFileWorker(FILE_PATH);
    private String[] data;

    private Facade facade;

    public ImporterImpl(Facade facade) {
        this.facade = facade;
        this.data = textFileWorker.readFromFile();
    }

    @Override
    public List<GuestModel> importGuests() {
        List<GuestModel> guests = new ArrayList<>();
        for(String line : data) {
            if(GuestValidator(line)) {
                guests.add(convertStringToGuest(line));
            }
        }
        return guests;
    }

    @Override
    public List<RegistrationModel> importRegistrations() {
        List<RegistrationModel> registrations = new ArrayList<>();
        for(String line : data) {
            if(RegistrationValidator(line)) {
                registrations.add(convertStringToRegistration(line));
            }
        }
        return registrations;
    }

    @Override
    public List<RoomModel> importRooms() {
        List<RoomModel> rooms = new ArrayList<>();
        for(String line : data) {
            if(RoomValidator(line)) {
                rooms.add(convertStringToRoom(line));
            }
        }
        return rooms;
    }

    @Override
    public List<ServiceModel> importServices() {
        List<ServiceModel> services = new ArrayList<>();
        for(String line : data) {
            if(ServiceValidator(line)) {
                services.add(convertStringToService(line));
            }
        }
        return services;
    }

    private GuestModel convertStringToGuest(String string) {
        GuestModel guestModel = new GuestModel();
        String[] params = string.split(";");
        guestModel.setGuestId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("S", "")));
        guestModel.setName(params[1]);
        guestModel.setStartDate(LocalDate.parse(params[2]));
        guestModel.setFinalDate(LocalDate.parse(params[3]));
        String[] values = params[4].split(",");
        List<ServiceModel> serviceModels = getServicesById(values);
        guestModel.setServiceModelList(serviceModels);
        return guestModel;
    }

    private RoomModel convertStringToRoom(String string) {
        RoomModel roomModel = new RoomModel();
        String[] params = string.split(";");
        roomModel.setRoomId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("R", "")));
        roomModel.setPrice(Double.parseDouble(params[1]));
        roomModel.setCapacity(Integer.parseInt(params[2]));
        roomModel.setStatus(RoomStatusValidator(params[3]));
        roomModel.setSection(RoomSectionValidator(params[4]));
        roomModel.setRating(Integer.parseInt(params[5]));
        String[] values = params[6].split(",");
        List<GuestModel> guestModels = getGuestsById(values);
        roomModel.setGuests(guestModels);
        return roomModel;
    }

    private ServiceModel convertStringToService(String string) {
        ServiceModel serviceModel = new ServiceModel();
        String[] params = string.split(";");
        serviceModel.setServiceId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("S", "")));
        serviceModel.setName(params[1]);
        serviceModel.setPrice(Double.parseDouble(params[2]));
        serviceModel.setSection(ServiceSectionValidator(params[3]));
        serviceModel.setStartDate(LocalDate.parse(params[4]));
        serviceModel.setFinalDate(LocalDate.parse(params[5]));
        return serviceModel;
    }

    private RegistrationModel convertStringToRegistration(String string) {
        RegistrationModel registrationModel = new RegistrationModel();
        String[] params = string.split(";");
        registrationModel.setRegistrationId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("T", "")));
        registrationModel.setGuestId(Integer.parseInt(params[1]));
        registrationModel.setRoomId(Integer.parseInt(params[2]));
        registrationModel.setStartDate(LocalDate.parse(params[3]));
        registrationModel.setFinalDate(LocalDate.parse(params[4]));
        return registrationModel;
    }

    private boolean GuestValidator(String string) {
        String[] values = string.split(";");
        for (String value : values) {
            if (value.replace("[", "").replace("]", "").replace(", ", "").charAt(0) == 'G') {
                return true;
            }
        }
        return false;
    }

    private boolean RoomValidator(String string) {
        String[] values = string.split(";");
        for (String value : values) {
            if (value.replace("[", "").replace("]", "").replace(", ", "").charAt(0) == 'R') {
                return true;
            }
        }
        return false;
    }

    private boolean ServiceValidator(String string) {
        String[] values = string.split(";");
        for (String value : values) {
            if (value.replace("[", "").replace("]", "").replace(", ", "").charAt(0) == 'S') {
                return true;
            }
        }
        return false;
    }

    private boolean RegistrationValidator(String string) {
        String[] values = string.split(";");
        for (String value : values) {
            if (value.replace("[", "").replace("]", "").replace(", ", "").charAt(0) == 'T') {
                return true;
            }
        }
        return false;
    }

    private List<ServiceModel> getServicesById(String[] services) {
        List<ServiceModel> serviceModels = new ArrayList<>();
        for(String id : services) {
            serviceModels.add(facade.getService(Integer.parseInt(id)));
        }
        return serviceModels;
    }

    private List<GuestModel> getGuestsById(String[] guests) {
        List<GuestModel> guestModels = new ArrayList<>();
        for(String id : guests) {
            guestModels.add(facade.getGuest(Integer.parseInt(id)));
        }
        return guestModels;
    }

    private List<RoomModel> getRoomsById(String[] guests) {
        List<RoomModel> roomModels = new ArrayList<>();
        for(String id : guests) {
            roomModels.add(facade.getRoom(Integer.parseInt(id)));
        }
        return roomModels;
    }

    private List<RegistrationModel> getRegistrationsById(String[] guests) {
        List<RegistrationModel> registrationModels = new ArrayList<>();
        for(String id : guests) {
            registrationModels.add(facade.getRegistration(Integer.parseInt(id)));
        }
        return registrationModels;
    }

    private RoomStatus RoomStatusValidator(String string) {
        switch (string) {
            case "MAINTAINED":
                return RoomStatus.MAINTAINED;
            case "FREE":
                return RoomStatus.FREE;
            case "BUSY":
                return RoomStatus.BUSY;
        }

        return null;
    }

    private RoomsSection RoomSectionValidator(String string) {
        switch (string) {
            case "STANDART":
                return RoomsSection.STANDART;
            case "SINGLE":
                return RoomsSection.SINGLE;
            case "LUKS":
                return RoomsSection.LUKS;
            case "IMPROVED":
                return RoomsSection.IMPROVED;
        }

        return null;
    }

    private ServicesSection ServiceSectionValidator(String string) {
        switch (string) {
            case "FOOD":
                return ServicesSection.FOOD;
            case "MANDATORY":
                return ServicesSection.MANDATORY;
            case "PLACE":
                return ServicesSection.PLACE;
        }
        return null;
    }
}
