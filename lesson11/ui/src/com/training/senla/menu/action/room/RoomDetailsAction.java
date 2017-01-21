package com.training.senla.menu.action.room;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.model.RoomModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomDetailsAction implements Action{
    private static final Logger LOG = LogManager.getLogger(RoomDetailsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        int roomId = Reader.getInt("Input room ID: ");
        List<Object> objects = new ArrayList<>();
        try {
            objects.add(roomId);
            DataPacket packet = new DataPacket("getRoom", objects);
            RoomModel room = (RoomModel) requestHandler.sendRequest(packet);
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
