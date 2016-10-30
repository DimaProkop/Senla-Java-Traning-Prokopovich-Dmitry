package com.training.senla.menu.item.room;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomsSortedByRating extends Item{
    private static final Logger LOG = LogManager.getLogger(RoomsSortedByRating.class);
    public RoomsSortedByRating(Menu menu) {
        super("Rooms sorted by rating", menu);
    }

    @Override
    public Menu execute() {
        try {
            List<RoomModel> rooms = facade.getSortedByRating();
            if(rooms == null) {
                PrintModel.printMessage("Rooms not found.");
            }else {
                PrintModel.printRooms(rooms);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
