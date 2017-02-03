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
public class ChangeStatusRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ChangeStatusRoomAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        int roomId = Reader.getInt("Input room ID: ");
        List<Object> objects = new ArrayList<>();
        try {
            objects.add(roomId);
            DataPacket packet = new DataPacket("getRoom", objects);
            Room room = (Room) requestHandler.sendRequest(packet);
            if (room == null) {
                PrintModel.printMessage("Room not found.");
            } else {
                objects.clear();
                objects.add(room);
                packet = new DataPacket("changeRoomStatus", objects);
                boolean truth = (boolean) requestHandler.sendRequest(packet);
                if (!truth) {
                    PrintModel.printMessage("Can't change room status!");
                } else {
                    PrintModel.printMessage("Room is maintained.");
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
