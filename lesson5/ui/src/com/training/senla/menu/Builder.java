package com.training.senla.menu;

import com.training.senla.facade.Facade;
import com.training.senla.menu.item.AddRoomItem;
import com.training.senla.menu.item.AllGuesItem;

/**
 * Created by prokop on 24.10.16.
 */
public class Builder {
    private Facade facade;

    public Builder(Facade facade) {
        this.facade = facade;
    }

    public Menu buildMenu() {
        Menu admin = new Menu("admin");

        admin.addItem(new AddRoomItem());
        admin.addItem(new AllGuesItem());

        return admin;
    }
}
