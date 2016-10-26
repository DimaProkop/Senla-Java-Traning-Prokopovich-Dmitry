package com.training.senla.menu;

import com.training.senla.facade.Facade;
import com.training.senla.menu.item.*;
import com.training.senla.menu.item.guest.*;
import com.training.senla.menu.item.registration.AllRegistrationsItem;
import com.training.senla.menu.item.room.*;
import com.training.senla.menu.item.service.AllServicesItem;
import com.training.senla.menu.item.service.ChangePriceInService;
import com.training.senla.menu.item.service.NewServiceItem;

/**
 * Created by prokop on 24.10.16.
 */
public class Builder {
    private Facade facade;

    public Builder(Facade facade) {
        this.facade = facade;
    }

    public Menu buildMenu() {
        Menu main = new Menu("<<<<< MAIN >>>>>");
        Menu guest = new Menu("GUEST");
        Menu room = new Menu("ROOM");
        Menu service = new Menu("SERVICE");
        Menu registration = new Menu("REGISTRATION");

        main.addItem(new OptionItem("____GUEST____", guest));
        main.addItem(new OptionItem("____ROOM____", room));
        main.addItem(new OptionItem("____SERVICE____", service));
        main.addItem(new OptionItem("____REGISTRATION____", registration));
        main.addItem(new OptionItem("Exit", null));

        guest.addItem(new NewGuestItem(guest, facade));
        guest.addItem(new AllGuestsItem(guest, facade));
        guest.addItem(new CountGuests(guest, facade));
        guest.addItem(new SettlementGuest(guest, facade));
        guest.addItem(new SumByRoomGuest(guest, facade));
        guest.addItem(new GuestEviction(guest, facade));
        guest.addItem(new OptionItem("Previous", main));

        room.addItem(new NewRoomItem(room, facade));
        room.addItem(new AllRoomsItem(room, facade));
        room.addItem(new RoomDetails(room, facade));
        room.addItem(new ChangePriceInRoom(room, facade));
        room.addItem(new CountFreeRooms(room, facade));
        room.addItem(new RoomsSortedByPrice(room, facade));
        room.addItem(new RoomsSortedByCapacity(room, facade));
        room.addItem(new RoomsSortedByRating(room, facade));
        room.addItem(new RoomsByFutureDate(room, facade));
        room.addItem(new OptionItem("Previous", main));

        service.addItem(new NewServiceItem(service, facade));
        service.addItem(new AllServicesItem(service, facade));
        service.addItem(new ChangePriceInService(service, facade));
        service.addItem(new OptionItem("Previous", main));

        registration.addItem(new AllRegistrationsItem(registration, facade));
        service.addItem(new OptionItem("Previous", main));
        return main;
    }
}
