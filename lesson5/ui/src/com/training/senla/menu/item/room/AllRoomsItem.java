package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
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
public class AllRoomsItem extends Item{
    private static final Logger LOG = LogManager.getLogger(AllRoomsItem.class);
    public AllRoomsItem(Menu menu, Facade facade) {
        super("All rooms", menu, facade);
    }

    @Override
    public Menu execute() {
        try {
            List<RoomModel> rooms = facade.getAllRooms();
            if(rooms == null || rooms.size() == 0) {
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