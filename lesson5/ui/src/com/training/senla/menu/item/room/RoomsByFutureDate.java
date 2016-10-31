package com.training.senla.menu.item.room;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomsByFutureDate extends Item{
    private static final Logger LOG = LogManager.getLogger(RoomsByFutureDate.class);
    public RoomsByFutureDate(Menu menu) {
        super("Rooms by future date", menu);
    }

    @Override
    public Menu execute() {
        Date date = Reader.getDate("Input date - (dd-mm-yyyy): ");
        try {
            List<RoomModel> rooms = FacadeImpl.getInstance().getReleasedRoomsInFuture(date);
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
