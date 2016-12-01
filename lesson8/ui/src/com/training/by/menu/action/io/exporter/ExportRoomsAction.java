package com.training.by.menu.action.io.exporter;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 7.11.16.
 */
public class ExportRoomsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ExportRoomsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getAllRooms", null);
            List<Room> rooms = (List<Room>) requestHandler.sendRequest(packet);
            if (rooms.size() == 0) {
                PrintModel.printMessage("Rooms is missing.");
            } else {
                packet = new DataPacket("exportRooms", null);
                requestHandler.sendRequest(packet);
                PrintModel.printMessage("Rooms have successfully exported.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
