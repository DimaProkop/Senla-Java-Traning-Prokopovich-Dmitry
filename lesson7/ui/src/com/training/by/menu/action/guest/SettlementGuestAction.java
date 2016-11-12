package com.training.by.menu.action.guest;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by prokop on 26.10.16.
 */
public class SettlementGuestAction implements Action {
    private static final Logger LOG = LogManager.getLogger(SettlementGuestAction.class);

    @Override
    public void execute() {
        int guestId = Reader.getInt("Input guest id: ");
        int roomId = Reader.getInt("Input room id: ");
        try {
            GuestModel guest = FacadeImpl.getInstance().getGuest(guestId);
            RoomModel room = FacadeImpl.getInstance().getRoom(roomId);
            if(guest == null || room == null) {
                PrintModel.printMessage("Guest or room not fount");
            } else {
                Date startDate = Reader.getDate("Input start date - (dd-mm-yyyy): ");
                Date finalDate = Reader.getDate("Input final date - (dd-mm-yyyy): ");
                FacadeImpl.getInstance().registerGuest(guest, room, startDate, finalDate);
                PrintModel.printMessage("Guest settled.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
