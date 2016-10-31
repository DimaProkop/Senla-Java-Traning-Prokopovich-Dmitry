package com.training.senla.menu.item.room;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangeInStatusRoom extends Item{
    private static final Logger LOG = LogManager.getLogger(ChangeInStatusRoom.class);
    public ChangeInStatusRoom(Menu menu) {
        super("Change status room", menu);
    }

    @Override
    public Menu execute() {
        int roomId = Reader.getInt("Input room ID: ");
        try {
            RoomModel room = FacadeImpl.getInstance().getRoom(roomId);
            if(room == null) {
                PrintModel.printMessage("Room not found.");
            }else {
                FacadeImpl.getInstance().changeRoomStatus(room);
                PrintModel.printMessage("Room is maintained.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
