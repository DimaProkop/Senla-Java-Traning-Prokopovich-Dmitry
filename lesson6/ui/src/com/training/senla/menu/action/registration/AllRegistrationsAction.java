package com.training.senla.menu.action.registration;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.action.Action;
import com.training.senla.model.RegistrationModel;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class AllRegistrationsAction implements Action{
    private static final Logger LOG = LogManager.getLogger(AllRegistrationsAction.class);

    @Override
    public void execute() {
        try {
            List<RegistrationModel> registrations = FacadeImpl.getInstance().getAllRegistrations();
            if(registrations == null || registrations.size() == 0) {
                PrintModel.printMessage("Registrations not found.");
            }else {
                PrintModel.printRegistrations(registrations);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
