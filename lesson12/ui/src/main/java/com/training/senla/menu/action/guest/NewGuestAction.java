package com.training.senla.menu.action.guest;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.model.Guest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class NewGuestAction implements Action {
    private static final Logger LOG = LogManager.getLogger(NewGuestAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        String name = Reader.getString("Input guest name: ");
        List<Object> objects = new ArrayList<>();

        try {
            Guest guest = new Guest(name);
            objects.add(guest);
            DataPacket packet = new DataPacket("addGuest", objects);
            requestHandler.sendRequest(packet);
            PrintModel.printMessage("Guest created.");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
