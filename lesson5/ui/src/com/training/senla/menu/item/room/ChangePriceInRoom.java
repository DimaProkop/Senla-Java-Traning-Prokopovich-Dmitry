package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.reader.Reader;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangePriceInRoom extends Item {
    public ChangePriceInRoom(Menu menu, Facade facade) {
        super("Change price in room", menu, facade);
    }

    @Override
    public Menu execute() {
        int roomId = Reader.getInt("Input room ID: ");
        RoomModel room = facade.getRoom(roomId);
        double value = Reader.getDouble("Input new price for room: ");
        facade.changeRoomPrice(room, value);
        return this.menu;
    }
}
