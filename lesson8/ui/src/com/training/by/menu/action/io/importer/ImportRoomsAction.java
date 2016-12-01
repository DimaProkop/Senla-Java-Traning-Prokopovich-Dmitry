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
public class ImportRoomsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ImportRoomsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("importRooms", null);
            requestHandler.sendRequest(packet);
            PrintModel.printMessage("Rooms have successfully imported.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
