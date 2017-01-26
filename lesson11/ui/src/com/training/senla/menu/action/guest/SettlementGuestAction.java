package com.training.senla.menu.action.guest;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
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
        List<Object> objects = new ArrayList<>();
        try {
            objects.add(guestId);
            DataPacket packet = new DataPacket("getGuest", objects);
            Guest guest = (Guest) requestHandler.sendRequest(packet);
            objects.clear();
            objects.add(roomId);
            packet = new DataPacket("getRoom", objects);
            Room room = (Room) requestHandler.sendRequest(packet);
            if(guest == null || room == null) {
                PrintModel.printMessage("Guest or room not fount");
            } else {
                Date startDate = Reader.getDate("Input start date - (dd-mm-yyyy): ");
                Date finalDate = Reader.getDate("Input final date - (dd-mm-yyyy): ");
                objects.clear();
                objects.add(guest);
                objects.add(room);
                objects.add(startDate);
                objects.add(finalDate);
                packet = new DataPacket("registerGuest", objects);
                requestHandler.sendRequest(packet);
                PrintModel.printMessage("Guest settled.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
