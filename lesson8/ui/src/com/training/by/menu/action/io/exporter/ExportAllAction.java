package com.training.by.menu.action.io.exporter;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ExportAllAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ExportAllAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("exportAll", null);
            requestHandler.sendRequest(packet);
            PrintModel.printMessage("Successful export.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
