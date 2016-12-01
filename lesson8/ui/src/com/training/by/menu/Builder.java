package com.training.by.menu;

import com.training.by.menu.action.OptionAction;
import com.training.by.menu.action.guest.*;
import com.training.by.menu.action.io.exporter.*;
import com.training.by.menu.action.io.importer.ImportGuestsAction;
import com.training.by.menu.action.io.importer.ImportRegistrationsAction;
import com.training.by.menu.action.io.importer.ImportRoomsAction;
import com.training.by.menu.action.io.importer.ImportServicesAction;
import com.training.by.menu.action.registration.AllRegistrationsAction;
import com.training.by.menu.action.room.*;
import com.training.by.menu.action.service.AllServicesAction;
import com.training.by.menu.action.service.ChangePriceInServiceAction;
import com.training.by.menu.action.service.NewServiceAction;

/**
 * Created by prokop on 24.10.16.
 */
public class Builder {

    public Builder() {
    }

    public Menu buildMenu() {
        Menu main = new Menu("<<<<< MAIN >>>>>");
        Menu guest = new Menu("GUEST");
        Menu room = new Menu("ROOM");
        Menu service = new Menu("SERVICE");
        Menu registration = new Menu("REGISTRATION");
        Menu io = new Menu("Import/Export");
        Menu importer = new Menu("Import model");
        Menu exporter = new Menu("Export model");

        main.addItem(new Item("____GUEST____", guest, new OptionAction()));
        main.addItem(new Item("____ROOM____", room, new OptionAction()));
        main.addItem(new Item("____SERVICE____", service, new OptionAction()));
        main.addItem(new Item("____REGISTRATION____", registration, new OptionAction()));
        main.addItem(new Item("____I/E____", io, new OptionAction()));
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

        io.addItem(new Item("__IMPORT__", importer, new OptionAction()));
        io.addItem(new Item("__EXPORT__", exporter, new OptionAction()));
        io.addItem(new Item("Previous", main, new OptionAction()));

        importer.addItem(new Item("Import guests", importer, new ImportGuestsAction()));
        importer.addItem(new Item("Import rooms", importer, new ImportRoomsAction()));
        importer.addItem(new Item("Import services", importer, new ImportServicesAction()));
        importer.addItem(new Item("Import registrations", importer, new ImportRegistrationsAction()));
        importer.addItem(new Item("Previous", io, new OptionAction()));

        exporter.addItem(new Item("Export guests", exporter, new ExportGuestsAction()));
        exporter.addItem(new Item("Export rooms", exporter, new ExportRoomsAction()));
        exporter.addItem(new Item("Export services", exporter, new ExportServicesAction()));
        exporter.addItem(new Item("Export registrations", exporter, new ExportRegistrationsAction()));
        exporter.addItem(new Item("Export all", exporter, new ExportAllAction()));
        exporter.addItem(new Item("Previous", io, new OptionAction()));


        return main;
    }
}
