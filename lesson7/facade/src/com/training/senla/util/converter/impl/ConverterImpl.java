package com.training.senla.util.converter.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.*;
import com.training.senla.util.converter.Converter;

import java.util.*;

/**
 * Created by prokop on 18.10.16.
 */
public class ConverterImpl implements Converter{


    public ConverterImpl() {

    }

    @Override
    public GuestModel convertStringToGuest(String string, ReadTemplate template) {
        GuestModel guestModel = new GuestModel();
        String[] params = string.split(template.getSeparator());
        List<Integer> orders = template.getOrderList();
        int max = Collections.max(orders);
        Map<Integer,Boolean> escapeMaps = template.getEscapeMaps();
        int id = Integer.parseInt(params[0]);
        guestModel.setId(id);
        if(!escapeMaps.get(orders.get(0))) {
            guestModel.setName("null");
        }else {
            guestModel.setName(params[orders.get(0)]);
        }
        ++max;
        if("null".equals(params[max])) {
            guestModel.setRoomModel(null);
        } else {
            guestModel.setRoomModel(FacadeImpl.getInstance().getRoom(Integer.parseInt(params[max])));
            guestModel.getRoomModel().addGuest(guestModel);
        }
        ++max;
        if("null".equals(params[max])) {
            guestModel.setServiceModelList(null);
        } else {
            String[] values = params[max].split(",");
            List<ServiceModel> serviceModels = getServicesById(values);
            guestModel.setServiceModelList(serviceModels);
        }
        return guestModel;
    }

    @Override
    public RoomModel convertStringToRoom(String string, ReadTemplate template) {
        RoomModel roomModel = new RoomModel();
        List<Integer> orders = template.getOrderList();
        int max = Collections.max(orders);
        Map<Integer,Boolean> escapeMaps = template.getEscapeMaps();
        String[] params = string.split(template.getSeparator());
        roomModel.setId(Integer.parseInt(params[0]));
        if(!escapeMaps.get(orders.get(0))) {
            roomModel.setPrice(0);
        }else {
            roomModel.setPrice(Double.parseDouble(params[orders.get(0)]));
        }

        if(!escapeMaps.get(orders.get(1))) {
            roomModel.setCapacity(0);
        }else {
            roomModel.setCapacity(Integer.parseInt(params[orders.get(1)]));
        }

        if(!escapeMaps.get(orders.get(2))) {
            roomModel.setStatus(null);
        }else {
            roomModel.setStatus(RoomStatus.isExist(params[orders.get(2)]));
        }

        if(!escapeMaps.get(orders.get(3))) {
            roomModel.setSection(null);
        }else {
            roomModel.setSection(RoomsSection.isExist(params[orders.get(3)]));
        }

        if(!escapeMaps.get(orders.get(4))) {
            roomModel.setRating(0);
        }else {
            roomModel.setRating(Integer.parseInt(params[orders.get(4)]));
        }

        ++max;
        if("null".equals(params[max])) {
            roomModel.setGuests(null);
        } else {
            String[] values = params[max].split(",");
            List<GuestModel> guestModels = getGuestsById(values);
            roomModel.setGuests(guestModels);
        }
        return roomModel;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ServiceModel convertStringToService(String string, ReadTemplate template) {
        ServiceModel serviceModel = new ServiceModel();
        List<Integer> orders = template.getOrderList();
        int max = Collections.max(orders);
        Map<Integer,Boolean> escapeMaps = template.getEscapeMaps();
        String[] params = string.split(template.getSeparator());
        serviceModel.setId(Integer.parseInt(params[0]));
        if(!escapeMaps.get(orders.get(0))) {
            serviceModel.setName("null");
        }else {
            serviceModel.setName(params[orders.get(0)]);
        }

        if(!escapeMaps.get(orders.get(1))) {
            serviceModel.setPrice(0);
        }else {
            serviceModel.setPrice(Double.parseDouble(params[orders.get(1)]));
        }

        if(!escapeMaps.get(orders.get(2))) {
            serviceModel.setSection(null);
        }else {
            serviceModel.setSection(ServicesSection.isExist(params[orders.get(2)]));
        }

        if(!escapeMaps.get(orders.get(3))) {
            serviceModel.setStartDate(null);
        }else {
            String[] startDates = params[orders.get(3)].split("-");
            serviceModel.setStartDate(new Date(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2])));
        }

        if(!escapeMaps.get(orders.get(4))) {
            serviceModel.setFinalDate(null);
        }else {
            String[] finalDates = params[orders.get(4)].split("-");
            serviceModel.setFinalDate(new Date(Integer.parseInt(finalDates[0]), Integer.parseInt(finalDates[1]), Integer.parseInt(finalDates[2])));
        }
        return serviceModel;
    }

    @SuppressWarnings("deprecation")
    @Override
    public RegistrationModel convertStringToRegistration(String string, ReadTemplate template) {
        RegistrationModel registrationModel = new RegistrationModel();
        List<Integer> orders = template.getOrderList();
        int max = Collections.max(orders);
        Map<Integer,Boolean> escapeMaps = template.getEscapeMaps();
        String[] params = string.split(template.getSeparator());
        registrationModel.setId(Integer.parseInt(params[0]));
        if(!escapeMaps.get(orders.get(0))) {
            registrationModel.setGuestId(0);
        }else {
            registrationModel.setGuestId(Integer.parseInt(params[orders.get(0)]));
        }

        if(!escapeMaps.get(orders.get(1))) {
            registrationModel.setRoomId(0);
        }else {
            registrationModel.setRoomId(Integer.parseInt(params[orders.get(1)]));
        }

        if(!escapeMaps.get(orders.get(2))) {
            registrationModel.setStartDate(null);
        }else {
            String[] startDates = params[orders.get(3)].split("-");
            registrationModel.setStartDate(new Date(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2])));
        }

        if(!escapeMaps.get(orders.get(3))) {
            registrationModel.setFinalDate(null);
        }else {
            String[] finalDates = params[orders.get(4)].split("-");
            registrationModel.setFinalDate(new Date(Integer.parseInt(finalDates[0]), Integer.parseInt(finalDates[1]), Integer.parseInt(finalDates[2])));
        }
        return registrationModel;
    }

    private List<ServiceModel> getServicesById(String[] services) {
        List<ServiceModel> serviceModels = new ArrayList<>();
        for (String id : services) {
            serviceModels.add(FacadeImpl.getInstance().getService((Integer.parseInt(id))));
        }
        return serviceModels;
    }

    private List<GuestModel> getGuestsById(String[] guests) {
        List<GuestModel> guestModels = new ArrayList<>();
        for (String id : guests) {
            guestModels.add(FacadeImpl.getInstance().getGuest((Integer.parseInt(id))));
        }
        return guestModels;
    }
}
