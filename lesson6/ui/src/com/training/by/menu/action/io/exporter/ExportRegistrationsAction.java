package com.training.by.menu.action.io.exporter;

import com.training.by.menu.action.Action;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.by.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ExportRegistrationsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ExportRegistrationsAction.class);

    @Override
    public void execute() {
        try {
            if (FacadeImpl.getInstance().getAllRegistrations().size() == 0) {
                PrintModel.printMessage("Registrations is missing.");
            } else {
                FacadeImpl.getInstance().exportRegistrations();
                PrintModel.printMessage("Registrations have successfully exported.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
