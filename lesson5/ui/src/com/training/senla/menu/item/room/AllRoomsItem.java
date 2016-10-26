package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class AllRoomsItem extends Item{
    public AllRoomsItem(Menu menu, Facade facade) {
        super("All rooms", menu, facade);
    }

    @Override
    public Menu execute() {
        List<RoomModel> rooms = facade.getAllRooms();
        PrintModel.printRooms(rooms);
        return this.menu;
    }
}
