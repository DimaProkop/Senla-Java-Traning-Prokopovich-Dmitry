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
public class NewGuestItem extends Item {
    public NewGuestItem(Menu menu, Facade facade) {
        super("Add new guest", menu, facade);
    }

    @Override
    public Menu execute() {
        String name = Reader.getString("Input guest name: ");
        GuestModel guest = new GuestModel(name);
        facade.setGuest(guest);
        PrintModel.printMessage("Guest created.");
        return this.menu;
    }
}
