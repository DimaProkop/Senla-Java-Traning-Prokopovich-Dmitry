package com.training.senla.menu;

import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
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

    public Builder(String path) {
        FacadeImpl.getInstance().init(path);
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

        guest.addItem(new NewGuestItem(guest));
        guest.addItem(new AllGuestsItem(guest));
        guest.addItem(new CountGuests(guest));
        guest.addItem(new SettlementGuest(guest));
        guest.addItem(new SumByRoomGuest(guest));
        guest.addItem(new GuestEviction(guest));
        guest.addItem(new OptionItem("Previous", main));

        room.addItem(new NewRoomItem(room));
        room.addItem(new AllRoomsItem(room));
        room.addItem(new RoomDetails(room));
        room.addItem(new ChangePriceInRoom(room));
        room.addItem(new ChangeInStatusRoom(room));
        room.addItem(new CountFreeRooms(room));
        room.addItem(new RoomsSortedByPrice(room));
        room.addItem(new RoomsSortedByCapacity(room));
        room.addItem(new RoomsSortedByRating(room));
        room.addItem(new RoomsByFutureDate(room));
        room.addItem(new OptionItem("Previous", main));

        service.addItem(new NewServiceItem(service));
        service.addItem(new AllServicesItem(service));
        service.addItem(new ChangePriceInService(service));
        service.addItem(new OptionItem("Previous", main));

        registration.addItem(new AllRegistrationsItem(registration));
        registration.addItem(new OptionItem("Previous", main));
        return main;
    }
}
