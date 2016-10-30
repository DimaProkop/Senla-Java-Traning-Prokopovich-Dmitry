package com.training.senla.menu.item;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;

/**
 * Created by prokop on 26.10.16.
 */
public class OptionItem extends Item {


    public OptionItem(Menu menu) {
        super(menu.getName(), menu);
    }

    public OptionItem(String name, Menu menu) {
        super(name, menu);

    }
    @Override
    public Menu execute() {
        return this.menu;
    }
}
