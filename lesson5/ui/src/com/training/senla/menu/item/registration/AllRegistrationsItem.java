package com.training.senla.menu.item.registration;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RegistrationModel;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class AllRegistrationsItem extends Item{
    private static final Logger LOG = LogManager.getLogger(AllRegistrationsItem.class);
    public AllRegistrationsItem(Menu menu) {
        super("All registrations", menu);
    }

    @Override
    public Menu execute() {
        try {
            List<RegistrationModel> registrations = facade.getAllRegistrations();
            if(registrations == null || registrations.size() == 0) {
                PrintModel.printMessage("Registrations not found.");
            }else {
                PrintModel.printRegistrations(registrations);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
