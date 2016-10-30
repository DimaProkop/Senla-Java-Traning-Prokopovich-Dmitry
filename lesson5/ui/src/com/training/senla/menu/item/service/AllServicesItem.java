package com.training.senla.menu.item.service;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.ServiceModel;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class AllServicesItem extends Item{
    private static final Logger LOG = LogManager.getLogger(AllServicesItem.class);

    public AllServicesItem(Menu menu) {
        super("All services", menu);
    }

    @Override
    public Menu execute() {
        try {
            List<ServiceModel> services = facade.getAllServices();
            if(services == null) {
                PrintModel.printMessage("Services not found.");
            }else {
                PrintModel.printServices(services);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
