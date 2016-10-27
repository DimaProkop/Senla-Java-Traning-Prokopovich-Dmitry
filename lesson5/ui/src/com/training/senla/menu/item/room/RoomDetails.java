package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
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
public class RoomDetails extends Item{
    private static final Logger LOG = LogManager.getLogger(RoomDetails.class);

    public RoomDetails(Menu menu, Facade facade) {
        super("Details room", menu, facade);
    }

    @Override
    public Menu execute() {
        int roomId = Reader.getInt("Input room ID: ");
        try {
            RoomModel room = facade.getRoom(roomId);
            if(room == null) {
                PrintModel.printMessage("Room not found.");
            }else {
                PrintModel.printRoom(room);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
