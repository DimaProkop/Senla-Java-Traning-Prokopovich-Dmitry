package com.training.senla.menu.action.io.importer;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ImportAllAction implements Action{
    private static final Logger LOG = LogManager.getLogger(ImportAllAction.class);

    @Override
    public void execute() {
        try {
            FacadeImpl.getInstance().importAll();
            PrintModel.printMessage("Successful import.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
