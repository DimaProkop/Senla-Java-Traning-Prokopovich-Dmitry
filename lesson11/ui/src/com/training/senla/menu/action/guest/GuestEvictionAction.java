package com.training.senla.menu.action.guest;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.model.GuestModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class GuestEvictionAction implements Action {
        private static final Logger LOG = LogManager.getLogger(GuestEvictionAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        int guestId = Reader.getInt("Input guest ID: ");
        List<Object> objects = new ArrayList<>();
        try {
            objects.add(guestId);
            DataPacket packet = new DataPacket("getGuest", objects);
            GuestModel guest = (GuestModel) requestHandler.sendRequest(packet);
            if(guest == null) {
                PrintModel.printMessage("Guest not found");
            }else {
                objects.clear();
                objects.add(guest);
                packet = new DataPacket("evictGuest", objects);
                requestHandler.sendRequest(packet);
                PrintModel.printMessage("Guest evicted.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
