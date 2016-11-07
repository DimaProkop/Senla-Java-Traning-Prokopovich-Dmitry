package com.training.senla.menu.action.io.importer;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ImportRegistrationsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ImportRegistrationsAction.class);

    @Override
    public void execute() {
        try {
            FacadeImpl.getInstance().importGuests();
            PrintModel.printMessage("Registrations have successfully imported.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
