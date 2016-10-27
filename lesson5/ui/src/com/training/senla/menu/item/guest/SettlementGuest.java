package com.training.senla.menu.item.guest;

import com.training.senla.facade.Facade;
import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;

/**
 * Created by prokop on 26.10.16.
 */
public class SettlementGuest extends Item{
    private static final Logger LOG = LogManager.getLogger(SettlementGuest.class);

    public SettlementGuest(Menu menu, Facade facade) {
        super("Register guest", menu, facade);
    }

    @Override
    public Menu execute() {
        int guestId = Reader.getInt("Input guest id: ");
        int roomId = Reader.getInt("Input room id: ");
        try {
            GuestModel guest = facade.getGuest(guestId);
            RoomModel room = facade.getRoom(roomId);
            if(guest == null || room == null) {
                PrintModel.printMessage("Guest or room not fount");
            } else {
                LocalDate startDate = Reader.getDate("Input start date: ");
                LocalDate finalDate = Reader.getDate("Input final date: ");
                facade.registerGuest(guest, room, startDate, finalDate);
                PrintModel.printMessage("Guest settled.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
