package com.training.by.menu.action.guest;


import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.model.GuestModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


/**
 * Created by prokop on 24.10.16.
 */
public class AllGuestsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(AllGuestsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getAllGuests", null);
            List<GuestModel> guests = (List<GuestModel>) requestHandler.sendRequest(packet);
            if (guests == null || guests.size() == 0) {
                PrintModel.printMessage("Guests not found.");
            } else {
                PrintModel.printGuests(guests);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
