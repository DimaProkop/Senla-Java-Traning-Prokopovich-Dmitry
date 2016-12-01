package com.training.by.menu.action.room;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.model.Room;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomDetailsAction implements Action{
    private static final Logger LOG = LogManager.getLogger(RoomDetailsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        int roomId = Reader.getInt("Input room ID: ");
        try {
            DataPacket packet = new DataPacket("getRoom", roomId);
            Room room = (Room) requestHandler.sendRequest(packet);
            if(room == null) {
                PrintModel.printMessage("Room not found.");
            }else {
                PrintModel.printRoom(room);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
