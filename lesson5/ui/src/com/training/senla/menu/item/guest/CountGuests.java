package com.training.senla.menu.item.guest;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class CountGuests extends Item {
    private static final Logger LOG = LogManager.getLogger(CountGuests.class);
    public CountGuests(Menu menu) {
        super("Count guests", menu);
    }

    @Override
    public Menu execute() {
        try {
            int count = facade.getCountGuests();
            if(count == 0) {
                PrintModel.printMessage("Guests not found.");
            }else {
                PrintModel.printMessage(String.valueOf(count));
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
