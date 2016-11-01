package com.training.senla.menu;

import com.training.senla.menu.action.Action;

/**
 * Created by prokop on 24.10.16.
 */
public class Item {

    protected String name;
    protected Menu menu;
    private Action action;

    public Item(String name, Menu menu, Action action) {
        this.name = name;
        this.menu = menu;
        this.action = action;
    }

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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
