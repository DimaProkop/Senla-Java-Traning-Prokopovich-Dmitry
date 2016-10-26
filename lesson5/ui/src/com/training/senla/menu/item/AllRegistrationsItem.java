package com.training.senla.menu.item;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RegistrationModel;
import com.training.senla.print.PrintModel;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class AllRegistrationsItem extends Item{
    public AllRegistrationsItem(Menu menu, Facade facade) {
        super("All registrations", menu, facade);
    }

    @Override
    public Menu execute() {
        List<RegistrationModel> registrations = facade.getAllRegistrations();
        return this.menu;
    }
}
