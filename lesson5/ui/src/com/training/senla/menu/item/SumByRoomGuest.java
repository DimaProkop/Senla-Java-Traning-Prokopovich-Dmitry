package com.training.senla.menu.item;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;

/**
 * Created by prokop on 26.10.16.
 */
public class SumByRoomGuest extends Item {


    public SumByRoomGuest(Menu menu, Facade facade) {
        super("Sum for room guest", menu, facade);
    }

    @Override
    public Menu execute() {
        int roomId = Reader.getInt("Input room ID: ");
        int guestId = Reader.getInt("Input guest ID:");
        GuestModel guest = facade.getGuest(guestId);
        RoomModel room = facade.getRoom(roomId);
        double sum = facade.getSumPaymentRoom(guest, room);
        PrintModel.printMessage("Sum: " + String.valueOf(sum));
        return this.menu;
    }
}
