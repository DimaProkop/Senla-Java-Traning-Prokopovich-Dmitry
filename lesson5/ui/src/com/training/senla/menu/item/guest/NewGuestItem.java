package com.training.senla.menu.item.guest;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.GuestModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class NewGuestItem extends Item {
    private static final Logger LOG = LogManager.getLogger(NewGuestItem.class);
    public NewGuestItem(Menu menu, Facade facade) {
        super("Add new guest", menu, facade);
    }

    @Override
    public Menu execute() {
        String name = Reader.getString("Input guest name: ");
        try {
            GuestModel guest = new GuestModel(name);
            facade.setGuest(guest);
            PrintModel.printMessage("Guest created.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
