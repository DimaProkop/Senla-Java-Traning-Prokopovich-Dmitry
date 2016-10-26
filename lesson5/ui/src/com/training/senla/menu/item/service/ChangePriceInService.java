package com.training.senla.menu.item.service;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.ServiceModel;
import com.training.senla.reader.Reader;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangePriceInService extends Item {
    public ChangePriceInService(Menu menu, Facade facade) {
        super("Change price in service", menu, facade);
    }

    @Override
    public Menu execute() {
        int serviceId = Reader.getInt("Input service ID: ");
        ServiceModel service = facade.getService(serviceId);
        double value = Reader.getDouble("Input new price for service: ");
        facade.changeServicePrice(service, value);
        return this.menu;
    }
}
