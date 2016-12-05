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
        try {
            DataPacket packet = new DataPacket("getGuest", guestId);
            GuestModel guest = (GuestModel) requestHandler.sendRequest(packet);
            packet = new DataPacket("getRoom", roomId);
            RoomModel room = (RoomModel) requestHandler.sendRequest(packet);
            if(guest == null || room == null) {
                PrintModel.printMessage("Guest or room not found");
            }else {
                List<Object> params = new ArrayList<>();
                params.add(guest);
                params.add(room);
                packet = new DataPacket("getSumPaymentRoom", params);
                double sum = (double) requestHandler.sendRequest(packet);
                PrintModel.printMessage("Sum: " + String.valueOf(sum));
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
