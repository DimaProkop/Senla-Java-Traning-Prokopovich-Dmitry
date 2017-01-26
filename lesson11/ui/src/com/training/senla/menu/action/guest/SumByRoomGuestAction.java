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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class SumByRoomGuestAction implements Action {
    private static final Logger LOG = LogManager.getLogger(SumByRoomGuestAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        int roomId = Reader.getInt("Input room ID: ");
        int guestId = Reader.getInt("Input guest ID:");
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
                PrintModel.printMessage("Guest or room not found");
            }else {
                objects.clear();
                objects.add(guest);
                objects.add(room);
                packet = new DataPacket("getSumPaymentRoom", objects);
                double sum = (double) requestHandler.sendRequest(packet);
                PrintModel.printMessage("Sum: " + String.valueOf(sum));
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
