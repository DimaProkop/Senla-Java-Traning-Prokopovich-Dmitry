package com.training.senla.menu.item;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.print.PrintModel;

/**
 * Created by prokop on 26.10.16.
 */
public class CountGuests extends Item {
    public CountGuests(Menu menu, Facade facade) {
        super("Count guests", menu, facade);
    }

    @Override
    public Menu execute() {
        int count = facade.getCountGuests();
        PrintModel.printMessage(String.valueOf(count));
        return this.menu;
    }
}
