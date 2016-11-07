package com.training.senla.menu.action.io.exporter;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ExportAllAction implements Action{
    private static final Logger LOG = LogManager.getLogger(ExportAllAction.class);

    @Override
    public void execute() {
        try {
            FacadeImpl.getInstance().exportAll();
            PrintModel.printMessage("Successful export.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
