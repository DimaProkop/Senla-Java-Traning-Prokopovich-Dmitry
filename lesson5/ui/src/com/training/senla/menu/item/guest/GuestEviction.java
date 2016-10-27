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
public class GuestEviction extends Item{
        private static final Logger LOG = LogManager.getLogger(GuestEviction.class);
    public GuestEviction(Menu menu, Facade facade) {
        super("Evict guest", menu, facade);
    }

    @Override
    public Menu execute() {
        int guestId = Reader.getInt("Input guest ID: ");
        try {
            GuestModel guest = facade.getGuest(guestId);
            if(guest == null) {
                PrintModel.printMessage("Guest not found");
            }else {
                facade.evictGuest(guest);
                PrintModel.printMessage("Guest evicted.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}