package com.training.senla.menu.action.io.exporter;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.storage.Storage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 7.11.16.
 */
public class ExportRoomsAction implements Action{
    private static final Logger LOG = LogManager.getLogger(ExportRoomsAction.class);

    @Override
    public void execute() {
        try {
            if (Storage.rooms.size() == 0) {
                PrintModel.printMessage("Rooms is missing.");
            } else {
                FacadeImpl.getInstance().exportRooms();
                PrintModel.printMessage("Rooms have successfully exported.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
