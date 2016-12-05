package com.training.by.menu.action.guest;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by prokop on 26.10.16.
 */
public class SettlementGuestAction implements Action {
    private static final Logger LOG = LogManager.getLogger(SettlementGuestAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        int guestId = Reader.getInt("Input guest id: ");
        int roomId = Reader.getInt("Input room id: ");
        try {
            DataPacket packet = new DataPacket("getGuest", guestId);
            GuestModel guest = (GuestModel) requestHandler.sendRequest(packet);
            packet = new DataPacket("getRoom", roomId);
            RoomModel room = (RoomModel) requestHandler.sendRequest(packet);
            if(guest == null || room == null) {
                PrintModel.printMessage("Guest or room not fount");
            } else {
                Date startDate = Reader.getDate("Input start date - (dd-mm-yyyy): ");
                Date finalDate = Reader.getDate("Input final date - (dd-mm-yyyy): ");
                List<Object> params = new ArrayList<>();
                params.add(guest);
                params.add(room);
                params.add(startDate);
                params.add(finalDate);
                packet = new DataPacket("registerGuest", params);
                requestHandler.sendRequest(packet);
                PrintModel.printMessage("Guest settled.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
