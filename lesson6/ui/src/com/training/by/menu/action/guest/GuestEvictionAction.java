package com.training.by.menu.action.guest;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.GuestModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class GuestEvictionAction implements Action {
        private static final Logger LOG = LogManager.getLogger(GuestEvictionAction.class);

    @Override
    public void execute() {
        int guestId = Reader.getInt("Input guest ID: ");
        try {
            GuestModel guest = FacadeImpl.getInstance().getGuest(guestId);
            if(guest == null) {
                PrintModel.printMessage("Guest not found");
            }else {
                FacadeImpl.getInstance().evictGuest(guest);
                PrintModel.printMessage("Guest evicted.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
