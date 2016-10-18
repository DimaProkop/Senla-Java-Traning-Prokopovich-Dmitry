package com.training.senla.util.converter.impl;

import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.validator.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 18.10.16.
 */
public class ConverterImpl implements Converter{


    private Validator validator;

    public ConverterImpl() {
        this.validator = new Validator();
    }

    //Converters from string to object

    @Override
    public String convertGuestToString(GuestModel guestModel) {
        StringBuilder builder = new StringBuilder();
        builder.append("G");
        builder.append(String.valueOf(guestModel.getId()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getName()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getRoomModel().getId()));
        builder.append(";");
        for (ServiceModel serviceModel : guestModel.getServiceModelList()) {
            builder.append(serviceModel.getId());
            builder.append(",");
        }
        builder.append(";");
        return builder.toString();
    }

    @Override
    public String convertRoomToString(RoomModel roomModel) {
        StringBuilder builder = new StringBuilder();
        builder.append("R");
        builder.append(String.valueOf(roomModel.getId()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getPrice()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getCapacity()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getStatus()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getSection()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getRating()));
        builder.append(";");
        for (GuestModel guestModel : roomModel.getGuests()) {
            builder.append(guestModel.getId());
            builder.append(",");
        }
        builder.append(";");
        return builder.toString();
    }

    @Override
    public String convertServiceToString(ServiceModel serviceModel) {
        StringBuilder builder = new StringBuilder();
        builder.append("S");
        builder.append(String.valueOf(serviceModel.getId()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getName()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getPrice()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getSection()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getStartDate()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getFinalDate()));
        builder.append(";");
        return builder.toString();
    }

    @Override
    public String convertRegistrationToString(RegistrationModel registrationModel) {
        StringBuilder builder = new StringBuilder();
        builder.append("T");
        builder.append(String.valueOf(registrationModel.getId()));
        builder.append(";");
        builder.append(String.valueOf(registrationModel.getGuestId()));
        builder.append(";");
        builder.append(String.valueOf(registrationModel.getRoomId()));
        builder.append(";");
        builder.append(String.valueOf(registrationModel.getStartDate()));
        builder.append(";");
        builder.append(String.valueOf(registrationModel.getFinalDate()));
        builder.append(";");
        return builder.toString();
    }

    //Converters to model object

    @Override
    public GuestModel convertStringToGuest(String string, Facade facade) {
        GuestModel guestModel = new GuestModel();
        String[] params = string.split(";");
        guestModel.setId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("S", "")));
        guestModel.setName(params[1]);
        guestModel.setRoomModel(facade.getRoom(Integer.parseInt(params[2])));
        String[] values = params[3].split(",");
        List<ServiceModel> serviceModels = getServicesById(values, facade);
        guestModel.setServiceModelList(serviceModels);
        return guestModel;
    }

    @Override
    public RoomModel convertStringToRoom(String string, Facade facade) {
        RoomModel roomModel = new RoomModel();
        String[] params = string.split(";");
        roomModel.setId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("R", "")));
        roomModel.setPrice(Double.parseDouble(params[1]));
        roomModel.setCapacity(Integer.parseInt(params[2]));
        roomModel.setStatus(validator.RoomStatusValidator(params[3]));
        roomModel.setSection(validator.RoomSectionValidator(params[4]));
        roomModel.setRating(Integer.parseInt(params[5]));
        String[] values = params[6].split(",");
        List<GuestModel> guestModels = getGuestsById(values, facade);
        roomModel.setGuests(guestModels);
        return roomModel;
    }

    @Override
    public ServiceModel convertStringToService(String string) {
        ServiceModel serviceModel = new ServiceModel();
        String[] params = string.split(";");
        serviceModel.setId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("S", "")));
        serviceModel.setName(params[1]);
        serviceModel.setPrice(Double.parseDouble(params[2]));
        serviceModel.setSection(validator.ServiceSectionValidator(params[3]));
        serviceModel.setStartDate(LocalDate.parse(params[4]));
        serviceModel.setFinalDate(LocalDate.parse(params[5]));
        return serviceModel;
    }

    @Override
    public RegistrationModel convertStringToRegistration(String string) {
        RegistrationModel registrationModel = new RegistrationModel();
        String[] params = string.split(";");
        registrationModel.setId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("T", "")));
        registrationModel.setGuestId(Integer.parseInt(params[1]));
        registrationModel.setRoomId(Integer.parseInt(params[2]));
        registrationModel.setStartDate(LocalDate.parse(params[3]));
        registrationModel.setFinalDate(LocalDate.parse(params[4]));
        return registrationModel;
    }

    //Getters

    private List<ServiceModel> getServicesById(String[] services, Facade facade) {
        List<ServiceModel> serviceModels = new ArrayList<>();
        for(String id : services) {
            serviceModels.add(facade.getService(Integer.parseInt(id)));
        }
        return serviceModels;
    }

    private List<GuestModel> getGuestsById(String[] guests, Facade facade) {
        List<GuestModel> guestModels = new ArrayList<>();
        for(String id : guests) {
            guestModels.add(facade.getGuest(Integer.parseInt(id)));
        }
        return guestModels;
    }
}
