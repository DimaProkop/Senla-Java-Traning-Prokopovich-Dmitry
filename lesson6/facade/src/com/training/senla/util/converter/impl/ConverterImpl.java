package com.training.senla.util.converter.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.storage.Storage;
import com.training.senla.util.converter.Converter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by prokop on 18.10.16.
 */
public class ConverterImpl implements Converter{
    private static final Logger LOG = LogManager.getLogger(ConverterImpl.class);

    private final String SEPARATOR = ";";

    public ConverterImpl() {
    }

    //Converters from string to object

    @Override
    public String convertGuestToString(GuestModel guestModel) {
        StringBuilder builder = new StringBuilder();
        builder.append("GG");
        builder.append(String.valueOf(guestModel.getId()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(guestModel.getName()));
        builder.append(SEPARATOR);
        if(guestModel.getRoomModel() == null) {
            builder.append(" ");
        } else {
            builder.append(String.valueOf(guestModel.getRoomModel().getId()));
        }
        builder.append(SEPARATOR);
        if(guestModel.getServiceModelList() == null) {
            builder.append(" ");
        } else {
            for (ServiceModel serviceModel : guestModel.getServiceModelList()) {
                builder.append(serviceModel.getId());
                builder.append(",");
            }
            builder.deleteCharAt(builder.length()-1);
        }
        builder.append(SEPARATOR);
        return builder.toString();
    }

    @Override
    public String convertRoomToString(RoomModel roomModel) {
        StringBuilder builder = new StringBuilder();
        builder.append("RR");
        builder.append(String.valueOf(roomModel.getId()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(roomModel.getPrice()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(roomModel.getCapacity()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(roomModel.getStatus()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(roomModel.getSection()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(roomModel.getRating()));
        builder.append(SEPARATOR);
        return builder.toString();
    }

    @Override
    public String convertServiceToString(ServiceModel serviceModel) {
        StringBuilder builder = new StringBuilder();
        builder.append("SS");
        builder.append(String.valueOf(serviceModel.getId()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(serviceModel.getName()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(serviceModel.getPrice()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(serviceModel.getSection()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(serviceModel.getStartDate()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(serviceModel.getFinalDate()));
        builder.append(SEPARATOR);
        return builder.toString();
    }

    @Override
    public String convertRegistrationToString(RegistrationModel registrationModel) {
        StringBuilder builder = new StringBuilder();
        builder.append("TT");
        builder.append(String.valueOf(registrationModel.getId()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(registrationModel.getGuestId()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(registrationModel.getRoomId()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(registrationModel.getStartDate()));
        builder.append(SEPARATOR);
        builder.append(String.valueOf(registrationModel.getFinalDate()));
        builder.append(SEPARATOR);
        return builder.toString();
    }

    //Converters to model object

    @Override
    public GuestModel convertStringToGuest(String string, Map<Integer, RoomModel> roomsMap, Map<Integer, ServiceModel> servicesMap) {
        GuestModel guestModel = new GuestModel();
        String[] params = string.split(SEPARATOR);
        guestModel.setId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("G", "")));
        guestModel.setName(params[1]);
        if(" ".equals(params[2])) {
            guestModel.setRoomModel(null);
        } else {
            guestModel.setRoomModel(roomsMap.get(Integer.parseInt(params[2])));
            guestModel.getRoomModel().addGuest(guestModel);
        }
        if(" ".equals(params[3])) {
            guestModel.setServiceModelList(null);
        } else {
            String[] values = params[3].split(",");
            List<ServiceModel> serviceModels = getServicesById(values, servicesMap);
            guestModel.setServiceModelList(serviceModels);
        }
        return guestModel;
    }

    @Override
    public RoomModel convertStringToRoom(String string) {
        RoomModel roomModel = new RoomModel();
        String[] params = string.split(SEPARATOR);
        roomModel.setId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("R", "")));
        roomModel.setPrice(Double.parseDouble(params[1]));
        roomModel.setCapacity(Integer.parseInt(params[2]));
        roomModel.setStatus(RoomStatus.isExist(params[3]));
        roomModel.setSection(RoomsSection.isExist(params[4]));
        roomModel.setRating(Integer.parseInt(params[5]));
        roomModel.setGuests(null);
        return roomModel;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ServiceModel convertStringToService(String string) {
        ServiceModel serviceModel = new ServiceModel();
        String[] params = string.split(SEPARATOR);
        serviceModel.setId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("S", "")));
        serviceModel.setName(params[1]);
        serviceModel.setPrice(Double.parseDouble(params[2]));
        serviceModel.setSection(ServicesSection.isExist(params[3]));
        String[] startDates = params[4].split("-");
        serviceModel.setStartDate(new Date(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2])));
        String[] finalDates = params[5].split("-");
        serviceModel.setFinalDate(new Date(Integer.parseInt(finalDates[0]), Integer.parseInt(finalDates[1]), Integer.parseInt(finalDates[2])));
        return serviceModel;
    }

    @SuppressWarnings("deprecation")
    @Override
    public RegistrationModel convertStringToRegistration(String string) {
        RegistrationModel registrationModel = new RegistrationModel();
        String[] params = string.split(SEPARATOR);
        registrationModel.setId(Integer.parseInt(params[0].replace("[", "").replace("]", "").replace(", ", "").replace("T", "")));
        registrationModel.setGuestId(Integer.parseInt(params[1]));
        registrationModel.setRoomId(Integer.parseInt(params[2]));
        String[] startDates = params[3].split("-");
        registrationModel.setStartDate(new Date(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2])));
        String[] finalDates = params[4].split("-");
        registrationModel.setFinalDate(new Date(Integer.parseInt(finalDates[0]), Integer.parseInt(finalDates[1]), Integer.parseInt(finalDates[2])));
        return registrationModel;
    }

    //Getters

    private List<ServiceModel> getServicesById(String[] services, Map<Integer, ServiceModel> servicesMap) {
        List<ServiceModel> serviceModels = new ArrayList<>();
        for (String id : services) {
            serviceModels.add(servicesMap.get(Integer.parseInt(id)));
        }
        return serviceModels;
    }
    @Override
    public List<Object> convertDataToObject() {
        List<Object> data = new ArrayList<>();
        data.add(Storage.guests);
        data.add(Storage.rooms);
        data.add(Storage.services);
        data.add(Storage.registrations);
        return data;
    }

    @Override
    public void convertDataToModel(List<Object> data) {
        try {
            Storage.guests = (List<GuestModel>) data.get(0);
            Storage.rooms = (List<RoomModel>) data.get(1);
            Storage.services = (List<ServiceModel>) data.get(2);
            Storage.registrations = (List<RegistrationModel>) data.get(3);
        }catch (Exception e) {
            LOG.error(e);
        }
    }
}
