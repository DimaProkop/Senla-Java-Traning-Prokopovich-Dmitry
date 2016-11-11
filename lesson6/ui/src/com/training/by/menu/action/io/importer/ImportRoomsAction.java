package com.training.by.menu.action.io.importer;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.senla.facade.impl.FacadeImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ImportRoomsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ImportRoomsAction.class);

    @Override
    public void execute() {
        try {
            FacadeImpl.getInstance().importGuests();
            PrintModel.printMessage("Rooms have successfully imported.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
