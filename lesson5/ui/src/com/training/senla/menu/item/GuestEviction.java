package com.training.senla.menu.item;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.GuestModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;

/**
 * Created by prokop on 26.10.16.
 */
public class GuestEviction extends Item {
    public GuestEviction(Menu menu, Facade facade) {
        super("Evict guest", menu, facade);
    }

    @Override
    public Menu execute() {
        int guestId = Reader.getInt("Input guest ID: ");
        GuestModel guest = facade.getGuest(guestId);
        facade.evictGuest(guest);
        PrintModel.printMessage("Guest evicted.");
        return this.menu;
    }
}
