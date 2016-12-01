package com.training.by.menu.action.io.importer;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ImportServicesAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ImportServicesAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("importServices", null);
            requestHandler.sendRequest(packet);
            PrintModel.printMessage("Services have successfully imported.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
