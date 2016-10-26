package com.training.senla.menu.item;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;

/**
 * Created by prokop on 26.10.16.
 */
public class SettlementGuest extends Item{


    public SettlementGuest(Menu menu, Facade facade) {
        super("Register guest", menu, facade);
    }

    @Override
    public Menu execute() {
        

        return this.menu;
    }
}
