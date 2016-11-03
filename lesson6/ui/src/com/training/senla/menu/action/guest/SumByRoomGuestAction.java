package com.training.senla.menu.action.guest;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.menu.action.Action;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class SumByRoomGuestAction implements Action {
    private static final Logger LOG = LogManager.getLogger(SumByRoomGuestAction.class);

    @Override
    public void execute() {
        int roomId = Reader.getInt("Input room ID: ");
        int guestId = Reader.getInt("Input guest ID:");
        try {
            GuestModel guest = FacadeImpl.getInstance().getGuest(guestId);
            RoomModel room = FacadeImpl.getInstance().getRoom(roomId);
            if(guest == null || room == null) {
                PrintModel.printMessage("Guest or room not found");
            }else {
                double sum = FacadeImpl.getInstance().getSumPaymentRoom(guest, room);
                PrintModel.printMessage("Sum: " + String.valueOf(sum));
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
