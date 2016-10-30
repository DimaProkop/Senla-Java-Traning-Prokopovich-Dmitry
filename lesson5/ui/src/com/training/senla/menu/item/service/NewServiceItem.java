package com.training.senla.menu.item.service;

import com.training.senla.enums.ServicesSection;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.ServiceModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by prokop on 26.10.16.
 */
public class NewServiceItem extends Item {
    private static final Logger LOG = LogManager.getLogger(NewServiceItem.class);
    public NewServiceItem(Menu menu) {
        super("Add new service", menu);
    }

    @Override
    public Menu execute() {
        try {
            String name = Reader.getString("Input service name: ");
            double price = Reader.getDouble("Input price: ");
            String strSection = Reader.getString("Input room section: ");
            ServicesSection section = ServicesSection.isExist(strSection.toUpperCase());
            Date startDate = Reader.getDate("Input start date - (dd-mm-yyyy): ");
            Date finalDate = Reader.getDate("Input final date - (dd-mm-yyyy): ");
            ServiceModel service = new ServiceModel(name, price, section, startDate, finalDate);
            facade.addService(service);
            PrintModel.printMessage("Service created.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
