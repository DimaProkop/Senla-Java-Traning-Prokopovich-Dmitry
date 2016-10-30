package com.training.senla.menu.item.guest;

import com.training.senla.menu.Item;
import com.training.senla.menu.Menu;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.Date;

/**
 * Created by prokop on 26.10.16.
 */
public class SettlementGuest extends Item{
    private static final Logger LOG = LogManager.getLogger(SettlementGuest.class);

    public SettlementGuest(Menu menu) {
        super("Register guest", menu);
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
                Date startDate = Reader.getDate("Input start date - (dd-mm-yyyy): ");
                Date finalDate = Reader.getDate("Input final date - (dd-mm-yyyy): ");
                facade.registerGuest(guest, room, startDate, finalDate);
                PrintModel.printMessage("Guest settled.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return this.menu;
    }
}
