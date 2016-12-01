package com.training.by.menu.action.io.exporter;

import com.training.by.menu.action.Action;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.by.print.PrintModel;
import com.training.senla.model.Guest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 7.11.16.
 */
public class ExportGuestsAction implements Action {

    private static final Logger LOG = LogManager.getLogger(ExportGuestsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getAllGuests", null);
            List<Guest> guests = (List<Guest>) requestHandler.sendRequest(packet);
            if (guests.size() == 0) {
                PrintModel.printMessage("Guests is missing.");
            } else {
                packet = new DataPacket("exportGuests", null);
                requestHandler.sendRequest(packet);
                PrintModel.printMessage("Guests have successfully exported.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
