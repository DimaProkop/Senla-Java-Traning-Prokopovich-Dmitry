package com.training.senla.menu.item.room;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomsByFutureDate extends Item{
    private static final Logger LOG = LogManager.getLogger(RoomsByFutureDate.class);
    public RoomsByFutureDate(Menu menu, Facade facade) {
        super("Rooms by future date", menu, facade);
    }

    @Override
    public Menu execute() {
        LocalDate date = Reader.getDate("Input date: ");
        try {
            List<RoomModel> rooms = facade.getReleasedRoomsInFuture(date);
            if (rooms == null) {
                PrintModel.printMessage("All rooms are occupied on this date.");
            }else {
                PrintModel.printRooms(rooms);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
