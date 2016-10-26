package com.training.senla.menu.item;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.GuestModel;
import com.training.senla.print.PrintModel;

import java.util.List;

/**
 * Created by prokop on 24.10.16.
 */
public class AllGuestsItem extends Item {

    public AllGuestsItem(Menu menu, Facade facade) {
        super("All guest", menu, facade);
    }

    @Override
    public Menu execute() {
        List<GuestModel> guests = facade.getAllGuests();
        PrintModel.printGuests(guests);
        return this.menu;
    }
}
