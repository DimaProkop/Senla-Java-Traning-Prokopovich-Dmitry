package com.training.by.menu.action.room;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.model.RoomModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangeStatusRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ChangeStatusRoomAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
            int roomId = Reader.getInt("Input room ID: ");
            try {
                DataPacket packet = new DataPacket("getRoom", roomId);
                RoomModel room = (RoomModel) requestHandler.sendRequest(packet);
                if (room == null) {
                    PrintModel.printMessage("Room not found.");
                } else {
                    packet = new DataPacket("changeRoomStatus", room);
                    boolean truth = (boolean) requestHandler.sendRequest(packet);
                    if(!truth) {
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
