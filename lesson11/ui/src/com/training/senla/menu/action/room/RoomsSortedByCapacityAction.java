package com.training.senla.menu.action.room;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.model.RoomModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomsSortedByCapacityAction implements Action {
    private static final Logger LOG = LogManager.getLogger(RoomsSortedByCapacityAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getSortedByCapacity", null);
            List<RoomModel> rooms = (List<RoomModel>) requestHandler.sendRequest(packet);
            if(rooms == null) {
                PrintModel.printMessage("Rooms not found.");
            }else {
                PrintModel.printRooms(rooms);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
