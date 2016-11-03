package com.training.senla.menu;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.action.*;
import com.training.senla.menu.action.guest.*;
import com.training.senla.menu.action.registration.AllRegistrationsAction;
import com.training.senla.menu.action.room.*;
import com.training.senla.menu.action.service.AllServicesAction;
import com.training.senla.menu.action.service.ChangePriceInServiceAction;
import com.training.senla.menu.action.service.NewServiceAction;

/**
 * Created by prokop on 24.10.16.
 */
public class Builder {

    public Builder() {
        FacadeImpl.getInstance().init();
    }

    public Menu buildMenu() {
        Menu main = new Menu("<<<<< MAIN >>>>>");
        Menu guest = new Menu("GUEST");
        Menu room = new Menu("ROOM");
        Menu service = new Menu("SERVICE");
        Menu registration = new Menu("REGISTRATION");

        main.addItem(new Item("____GUEST____", guest, new OptionAction()));
        main.addItem(new Item("____ROOM____", room, new OptionAction()));
        main.addItem(new Item("____SERVICE____", service, new OptionAction()));
        main.addItem(new Item("____REGISTRATION____", registration, new OptionAction()));
        main.addItem(new Item("Exit", null, new OptionAction()));

        guest.addItem(new Item("Add new guest", guest, new NewGuestAction()));
        guest.addItem(new Item("All guests", guest, new AllGuestsAction()));
        guest.addItem(new Item("Count guests", guest, new CountGuestsAction()));
        guest.addItem(new Item("Register guest in room", guest, new SettlementGuestAction()));
        guest.addItem(new Item("Amount for accommodation", guest, new SumByRoomGuestAction()));
        guest.addItem(new Item("Evict guest", guest, new GuestEvictionAction()));
        guest.addItem(new Item("Previous", main, new OptionAction()));

        room.addItem(new Item("Add new room", room, new NewRoomAction()));
        room.addItem(new Item("All rooms", room, new AllRoomsAction()));
        room.addItem(new Item("Room detail", room, new RoomDetailsAction()));
        room.addItem(new Item("Clone room", room, new CloneRoomAction()));
        room.addItem(new Item("Change price in room", room, new ChangePriceInRoomAction()));
        room.addItem(new Item("Change status in room", room, new ChangeStatusRoomAction()));
        room.addItem(new Item("Count free rooms", room, new CountFreeRoomsAction()));
        room.addItem(new Item("Rooms sorted by price", room, new RoomsSortedByPriceAction()));
        room.addItem(new Item("Rooms sorted by capacity", room, new RoomsSortedByCapacityAction()));
        room.addItem(new Item("Rooms sorted by rating", room, new RoomsSortedByRatingAction()));
        room.addItem(new Item("Rooms by future date", room, new RoomsByFutureDateAction()));
        room.addItem(new Item("Previous", main, new OptionAction()));

        service.addItem(new Item("Add new service", service, new NewServiceAction()));
        service.addItem(new Item("All services", service, new AllServicesAction()));
        service.addItem(new Item("Change price in service", service, new ChangePriceInServiceAction()));
        service.addItem(new Item("Previous", main, new OptionAction()));

        registration.addItem(new Item("All registrations", registration, new AllRegistrationsAction()));
        registration.addItem(new Item("Previous", main, new OptionAction()));
        return main;
    }
}
