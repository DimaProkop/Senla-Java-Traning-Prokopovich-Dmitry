package com.training.by.menu.action.room;

import com.training.by.menu.action.Action;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.RoomModel;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangeStatusRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ChangeStatusRoomAction.class);

    @Override
    public void execute() {
        if(!FacadeImpl.getInstance().changeRoomStatus(null)) {
            PrintModel.printMessage("Can't change the status of the rooms!");
        }else {
            int roomId = Reader.getInt("Input room ID: ");
            try {
                RoomModel room = FacadeImpl.getInstance().getRoom(roomId);
                if (room == null) {
                    PrintModel.printMessage("Room not found.");
                } else {
                    FacadeImpl.getInstance().changeRoomStatus(room);
                    PrintModel.printMessage("Room is maintained.");
                }
            } catch (Exception e) {
                LOG.error(e.getMessage());
            }
        }
    }
}
