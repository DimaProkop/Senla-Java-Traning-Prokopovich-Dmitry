package com.training.senla.menu.action.room;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.model.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangePriceInRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ChangePriceInRoomAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        List<Object> objects = new ArrayList<>();
        int roomId = Reader.getInt("Input room ID: ");
        try {
            objects.add(roomId);
            DataPacket packet = new DataPacket("getRoom", objects);
            Room room = (Room) requestHandler.sendRequest(packet);
            if(room == null) {
                PrintModel.printMessage("Room not found.");
            }else {
                double value = Reader.getDouble("Input new price for room: ");
                objects.clear();
                objects.add(room);
                objects.add(value);
                packet = new DataPacket("changeRoomPrice", objects);
                requestHandler.sendRequest(packet);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
