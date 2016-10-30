package com.training.senla.menu.item.service;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.ServiceModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangePriceInService extends Item {
    private static final Logger LOG = LogManager.getLogger(ChangePriceInService.class);
    public ChangePriceInService(Menu menu) {
        super("Change price in service", menu);
    }

    @Override
    public Menu execute() {
        int serviceId = Reader.getInt("Input service ID: ");
        try {
            ServiceModel service = facade.getService(serviceId);
            if(service == null) {
                PrintModel.printMessage("Service not found.");
            }else {
                double value = Reader.getDouble("Input new price for service: ");
                facade.changeServicePrice(service, value);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
