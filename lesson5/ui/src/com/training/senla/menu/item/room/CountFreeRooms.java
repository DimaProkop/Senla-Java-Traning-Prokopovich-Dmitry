package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class CountFreeRooms extends Item {
    private static final Logger LOG = LogManager.getLogger(CountFreeRooms.class);
    public CountFreeRooms(Menu menu, Facade facade) {
        super("Count free rooms", menu, facade);
    }

    @Override
    public Menu execute() {
        try {
            int count = facade.getCountFreeRooms();
            if(count == 0) {
                PrintModel.printMessage("No free rooms");
            }else {
                PrintModel.printMessage(String.valueOf(count));
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
