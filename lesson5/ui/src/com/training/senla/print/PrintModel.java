package com.training.senla.print;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.util.List;

/**
 * Created by prokop on 24.10.16.
 */
public class PrintModel {

    public static void printGuest(GuestModel guest) {
        System.out.printf("id:    name:      room:\n");
        System.out.printf("%d       %s        %d\n", guest.getId(), guest.getName(), guest.getRoomModel() == null ? "does't have room\n" : guest.getRoomModel().getId());
    }

    public static void printGuests(List<GuestModel> guests) {
        guests.forEach(PrintModel::printGuest);
    }

    public static void printRoom(RoomModel room) {
        System.out.printf("id:    price:      capacity:    status:       section:       rating:\n");
        System.out.printf("%d    %f      %d          %s          %s          %d\n", room.getId(), room.getPrice(), room.getCapacity(), room.getStatus().toString(), room.getSection().toString(), room.getRating());
    }

    public static void printRooms(List<RoomModel> rooms) {
        rooms.forEach(PrintModel::printRoom);
    }

    public static void printService(ServiceModel service) {
        System.out.printf("id:    name:      price:      section:        startDate:      finalDate:\n");
        System.out.printf("%d    %s      %f    %s      %s      %s\n", service.getId(), service.getName(), service.getPrice(), service.getSection().toString(), service.getStartDate().toString(), service.getFinalDate().toString());
    }

    public static void printServices(List<ServiceModel> services) {
        services.forEach(PrintModel::printService);
    }

    public static void printRegistration(RegistrationModel registration) {
        System.out.printf("id:   guestId:    roomId:    startDate:      finalDate:\n");
        System.out.printf("%d   %d    %d    %s      %s\n", registration.getId(), registration.getGuestId(), registration.getRoomId(), registration.getStartDate().toString(), registration.getFinalDate().toString());
    }

    public static void printRegistrations(List<RegistrationModel> registrations) {
        registrations.forEach(PrintModel::printRegistration);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

}
