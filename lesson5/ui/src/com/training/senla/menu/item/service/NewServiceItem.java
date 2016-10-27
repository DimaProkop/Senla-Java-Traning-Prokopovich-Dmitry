package com.training.senla.menu.item.service;

import com.sun.org.apache.regexp.internal.RE;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.ServiceModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.util.validator.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;

/**
 * Created by prokop on 26.10.16.
 */
public class NewServiceItem extends Item{
    private static final Logger LOG = LogManager.getLogger(NewServiceItem.class);
    private Validator validator;
    public NewServiceItem(Menu menu, Facade facade) {
        super("Add new service", menu, facade);
        this.validator = new Validator();
    }

    @Override
    public Menu execute() {
        try {
            String name = Reader.getString("Input service name: ");
            double price = Reader.getDouble("Input price: ");
            String strSection = Reader.getString("Input room section: ");
            ServicesSection section = validator.ServiceSectionValidator(strSection.toUpperCase());
            LocalDate startDate = Reader.getDate("Input start date: ");
            LocalDate finalDate = Reader.getDate("Input final date: ");
            ServiceModel service = new ServiceModel(name, price, section, startDate, finalDate);
            facade.setService(service);
            PrintModel.printMessage("Service created.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
