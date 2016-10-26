package com.training.senla.menu.item.service;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.ServiceModel;
import com.training.senla.print.PrintModel;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class AllServicesItem extends Item{


    public AllServicesItem(Menu menu, Facade facade) {
        super("All services", menu, facade);
    }

    @Override
    public Menu execute() {
        List<ServiceModel> services = facade.getAllServices();
        PrintModel.printServices(services);
        return this.menu;
    }
}
