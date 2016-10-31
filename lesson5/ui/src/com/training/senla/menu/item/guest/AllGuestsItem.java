package com.training.senla.menu.item.guest;

import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.GuestModel;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 24.10.16.
 */
public class AllGuestsItem extends Item {
    private static final Logger LOG = LogManager.getLogger(AllGuestsItem.class);

    public AllGuestsItem(Menu menu) {
        super("All guest", menu);
    }

    @Override
    public Menu execute() {
        try {
            List<GuestModel> guests = FacadeImpl.getInstance().getAllGuests();
            if (guests == null || guests.size() == 0) {
                PrintModel.printMessage("Guests not found.");
            } else {
                PrintModel.printGuests(guests);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
