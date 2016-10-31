package com.training.senla.menu;

import com.training.senla.Main;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;

/**
 * Created by prokop on 24.10.16.
 */
public abstract class Item {

    protected String name;
    protected Menu menu;

    public Item(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
    }

    public abstract Menu execute();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
