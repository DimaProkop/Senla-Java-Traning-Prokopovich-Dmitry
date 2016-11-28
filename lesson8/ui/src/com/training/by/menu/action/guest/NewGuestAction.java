package com.training.by.menu.action.guest;

import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.by.menu.action.Action;
import com.training.senla.model.GuestModel;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class NewGuestAction implements Action {
    private static final Logger LOG = LogManager.getLogger(NewGuestAction.class);

    @Override
    public void execute() {
        String name = Reader.getString("Input guest name: ");
        try {
            GuestModel guest = new GuestModel(name);
            DataPacket packet = new DataPacket("addGuest", guest);
            RequestHandler.sendRequest(packet);
            PrintModel.printMessage("Guest created.");
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
