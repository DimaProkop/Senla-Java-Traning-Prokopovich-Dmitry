package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.print.PrintModel;

/**
 * Created by prokop on 26.10.16.
 */
public class CountFreeRooms extends Item {
    public CountFreeRooms(Menu menu, Facade facade) {
        super("Count free rooms", menu, facade);
    }

    @Override
    public Menu execute() {
        int count = facade.getCountFreeRooms();
        PrintModel.printMessage(String.valueOf(count));
        return this.menu;
    }
}
