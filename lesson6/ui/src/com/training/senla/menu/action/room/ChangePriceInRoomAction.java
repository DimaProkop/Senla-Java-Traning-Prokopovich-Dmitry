package com.training.senla.menu.action.room;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.action.Action;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangePriceInRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ChangePriceInRoomAction.class);

    @Override
    public void execute() {
        int roomId = Reader.getInt("Input room ID: ");
        try {
            RoomModel room = FacadeImpl.getInstance().getRoom(roomId);
            if(room == null) {
                PrintModel.printMessage("Room not found.");
            }else {
                double value = Reader.getDouble("Input new price for room: ");
                FacadeImpl.getInstance().changeRoomPrice(room, value);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
