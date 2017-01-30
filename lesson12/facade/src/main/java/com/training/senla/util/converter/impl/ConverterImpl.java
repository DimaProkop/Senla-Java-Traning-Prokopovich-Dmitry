package com.training.senla.util.converter.impl;

import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.model.*;
import com.training.senla.template.ReadTemplate;
import com.training.senla.util.converter.Converter;

import java.util.*;

/**
 * Created by prokop on 18.10.16.
 */
public class ConverterImpl implements Converter{

    private Facade facade;

    public ConverterImpl() {
        this.facade = (Facade) DependencyInjection.getInstance(Facade.class);
    }

    @Override
    public Guest convertStringToGuest(String string, ReadTemplate template) {
        Guest guest = new Guest();
        String[] params = string.split(template.getSeparator());
        List<Integer> orders = template.getOrderList();
        int max = Collections.max(orders);
        Map<Integer,Boolean> escapeMaps = template.getEscapeMaps();
        int id = Integer.parseInt(params[0]);
        guest.setId(id);
        if(!escapeMaps.get(orders.get(0))) {
            guest.setName("null");
        }else {
            guest.setName(params[orders.get(0)]);
        }
        ++max;
        if("null".equals(params[max])) {
            guest.setRoom(null);
        } else {
            guest.setRoom(facade.getRoom(Integer.parseInt(params[max])));
            //guest.getRoom().addGuest(guest);
        }
        ++max;
        if("null".equals(params[max])) {
            guest.setServiceList(null);
        } else {
            String[] values = params[max].split(",");
            List<Service> services = getServicesById(values);
            guest.setServiceList(services);
        }
        return guest;
    }

    @Override
    public Room convertStringToRoom(String string, ReadTemplate template) {
        Room room = new Room();
        List<Integer> orders = template.getOrderList();
        int max = Collections.max(orders);
        Map<Integer,Boolean> escapeMaps = template.getEscapeMaps();
        String[] params = string.split(template.getSeparator());
        room.setId(Integer.parseInt(params[0]));
        if(!escapeMaps.get(orders.get(0))) {
            room.setPrice(0);
        }else {
            room.setPrice(Double.parseDouble(params[orders.get(0)]));
        }

        if(!escapeMaps.get(orders.get(1))) {
            room.setCapacity(0);
        }else {
            room.setCapacity(Integer.parseInt(params[orders.get(1)]));
        }

        if(!escapeMaps.get(orders.get(2))) {
            room.setStatus(null);
        }else {
            room.setStatus(RoomStatus.isExist(params[orders.get(2)]));
        }

        if(!escapeMaps.get(orders.get(3))) {
            room.setSection(null);
        }else {
            room.setSection(RoomsSection.isExist(params[orders.get(3)]));
        }

        if(!escapeMaps.get(orders.get(4))) {
            room.setRating(0);
        }else {
            room.setRating(Integer.parseInt(params[orders.get(4)]));
        }

        ++max;
        if("null".equals(params[max])) {
            room.setGuests(null);
        } else {
            String[] values = params[max].split(",");
            List<Guest> guests = getGuestsById(values);
            room.setGuests(guests);
        }
        return room;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Service convertStringToService(String string, ReadTemplate template) {
        Service service = new Service();
        List<Integer> orders = template.getOrderList();
        int max = Collections.max(orders);
        Map<Integer,Boolean> escapeMaps = template.getEscapeMaps();
        String[] params = string.split(template.getSeparator());
        service.setId(Integer.parseInt(params[0]));
        if(!escapeMaps.get(orders.get(0))) {
            service.setName("null");
        }else {
            service.setName(params[orders.get(0)]);
        }

        if(!escapeMaps.get(orders.get(1))) {
            service.setPrice(0);
        }else {
            service.setPrice(Double.parseDouble(params[orders.get(1)]));
        }

        if(!escapeMaps.get(orders.get(2))) {
            service.setSection(null);
        }else {
            service.setSection(ServicesSection.isExist(params[orders.get(2)]));
        }

        if(!escapeMaps.get(orders.get(3))) {
            service.setStartDate(null);
        }else {
            String[] startDates = params[orders.get(3)].split("-");
            service.setStartDate(new Date(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2])));
        }

        if(!escapeMaps.get(orders.get(4))) {
            service.setFinalDate(null);
        }else {
            String[] finalDates = params[orders.get(4)].split("-");
            service.setFinalDate(new Date(Integer.parseInt(finalDates[0]), Integer.parseInt(finalDates[1]), Integer.parseInt(finalDates[2])));
        }
        return service;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Registration convertStringToRegistration(String string, ReadTemplate template) {
        Registration registration = new Registration();
        List<Integer> orders = template.getOrderList();
        int max = Collections.max(orders);
        Map<Integer,Boolean> escapeMaps = template.getEscapeMaps();
        String[] params = string.split(template.getSeparator());
        registration.setId(Integer.parseInt(params[0]));
        if(!escapeMaps.get(orders.get(0))) {
            registration.setGuestId(0);
        }else {
            registration.setGuestId(Integer.parseInt(params[orders.get(0)]));
        }

        if(!escapeMaps.get(orders.get(1))) {
            registration.setRoomId(0);
        }else {
            registration.setRoomId(Integer.parseInt(params[orders.get(1)]));
        }

        if(!escapeMaps.get(orders.get(2))) {
            registration.setStartDate(null);
        }else {
            String[] startDates = params[orders.get(3)].split("-");
            registration.setStartDate(new Date(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]), Integer.parseInt(startDates[2])));
        }

        if(!escapeMaps.get(orders.get(3))) {
            registration.setFinalDate(null);
        }else {
            String[] finalDates = params[orders.get(4)].split("-");
            registration.setFinalDate(new Date(Integer.parseInt(finalDates[0]), Integer.parseInt(finalDates[1]), Integer.parseInt(finalDates[2])));
        }
        return registration;
    }

    private List<Service> getServicesById(String[] services) {
        List<Service> servicesList = new ArrayList<>();
        for (String id : services) {
            servicesList.add(facade.getService((Integer.parseInt(id))));
        }
        return servicesList;
    }

    private List<Guest> getGuestsById(String[] guests) {
        List<Guest> guestsList = new ArrayList<>();
        for (String id : guests) {
            guestsList.add(facade.getGuest((Integer.parseInt(id))));
        }
        return guestsList;
    }
}
