package com.training.senla.menu.action.io.importer;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ImportRegistrationsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ImportRegistrationsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("importRegistration", null);
            requestHandler.sendRequest(packet);
            PrintModel.printMessage("Registrations have successfully imported.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
