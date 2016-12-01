package com.training.by.menu.action.room;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.model.Room;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomsByFutureDateAction implements Action {
    private static final Logger LOG = LogManager.getLogger(RoomsByFutureDateAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        Date date = Reader.getDate("Input date - (dd-mm-yyyy): ");
        try {
            DataPacket packet = new DataPacket("getReleasedRoomsInFuture", date);
            List<Room> rooms = (List<Room>) requestHandler.sendRequest(packet);
            if (rooms == null) {
                PrintModel.printMessage("All rooms are occupied on this date.");
            }else {
                PrintModel.printRooms(rooms);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
