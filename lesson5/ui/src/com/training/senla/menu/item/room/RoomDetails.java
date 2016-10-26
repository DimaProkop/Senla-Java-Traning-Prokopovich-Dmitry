package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomDetails extends Item{

    public RoomDetails(Menu menu, Facade facade) {
        super("Details room", menu, facade);
    }

    @Override
    public Menu execute() {
        int roomId = Reader.getInt("Input room ID: ");
        RoomModel room = facade.getRoom(roomId);
        PrintModel.printRoom(room);
        return this.menu;
    }
}
