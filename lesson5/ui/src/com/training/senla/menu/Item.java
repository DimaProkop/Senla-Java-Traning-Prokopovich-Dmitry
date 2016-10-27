package com.training.senla.menu;

import com.training.senla.facade.Facade;

/**
 * Created by prokop on 24.10.16.
 */
public abstract class Item {

    protected String name;
    protected Menu menu;
    protected Facade facade;

    public Item(String name, Menu menu) {
        this.name = name;
        this.menu = menu;
    }

    public Item(String name, Menu menu, Facade facade) {
        this.name = name;
        this.menu = menu;
        this.facade = facade;
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
