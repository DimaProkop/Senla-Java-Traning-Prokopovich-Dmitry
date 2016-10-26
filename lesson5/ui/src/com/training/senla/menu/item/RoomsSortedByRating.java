package com.training.senla.menu.item;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomsSortedByRating extends Item{
    public RoomsSortedByRating(Menu menu, Facade facade) {
        super("Rooms sorted by rating", menu, facade);
    }

    @Override
    public Menu execute() {
        List<RoomModel> rooms = facade.getSortedByRating();
        PrintModel.printRooms(rooms);
        return this.menu;
    }
}
