package com.training.senla.menu.item.guest;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;

import java.time.LocalDate;

/**
 * Created by prokop on 26.10.16.
 */
public class SettlementGuest extends Item{


    public SettlementGuest(Menu menu, Facade facade) {
        super("Register guest", menu, facade);
    }

    @Override
    public Menu execute() {
        int guestId = Reader.getInt("Input guest id: ");
        GuestModel guest = facade.getGuest(guestId);
        int roomId = Reader.getInt("Input room id: ");
        RoomModel room = facade.getRoom(roomId);
        LocalDate startDate = Reader.getDate("Input start date: ");
        LocalDate finalDate = Reader.getDate("Input final date: ");
        facade.registerGuest(guest, room, startDate, finalDate);
        PrintModel.printMessage("Guest settled.");
        return this.menu;
    }
}
