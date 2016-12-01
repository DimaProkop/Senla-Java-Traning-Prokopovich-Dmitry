package com.training.by.print;


import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by prokop on 24.10.16.
 */
public class PrintModel {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static void printGuest(Guest guest) {
        System.out.printf("id:    name:      room:\n");
        System.out.printf("%d       %s        %s\n", guest.getId(), guest.getName(), guest.getRoom() == null ? "does't have room" : String.valueOf(guest.getRoom().getId()));
    }

    public static void printGuests(List<Guest> guests) {
        guests.forEach(PrintModel::printGuest);
    }

    public static void printRoom(Room room) {
        System.out.printf("id:    price:      capacity:    status:       section:       rating:\n");
        System.out.printf("%d    %f      %d          %s          %s          %d\n", room.getId(), room.getPrice(), room.getCapacity(), room.getStatus().toString(), room.getSection().toString(), room.getRating());
    }

    public static void printRooms(List<Room> rooms) {
        rooms.forEach(PrintModel::printRoom);
    }

    public static void printService(Service service) {
        System.out.printf("id:    name:      price:      section:        startDate:      finalDate:\n");
        System.out.printf("%d    %s      %f    %s      %s      %s\n", service.getId(), service.getName(), service.getPrice(), service.getSection().toString(), formatter.format(service.getStartDate()), formatter.format(service.getFinalDate()));
    }

    public static void printServices(List<Service> services) {
        services.forEach(PrintModel::printService);
    }

    public static void printRegistration(Registration registration) {
        System.out.printf("id:   guestId:    roomId:    startDate:      finalDate:\n");
        System.out.printf("%d   %d    %d    %s      %s\n", registration.getId(), registration.getGuestId(), registration.getRoomId(), formatter.format(registration.getStartDate()), formatter.format(registration.getFinalDate()));
    }

    public static void printRegistrations(List<Registration> registrations) {
        registrations.forEach(PrintModel::printRegistration);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

}
