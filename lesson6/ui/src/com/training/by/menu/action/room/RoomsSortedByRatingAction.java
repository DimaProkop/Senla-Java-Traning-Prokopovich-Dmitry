package com.training.by.menu.action.room;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.by.menu.action.Action;
import com.training.senla.model.RoomModel;
import com.training.by.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class RoomsSortedByRatingAction implements Action {
    private static final Logger LOG = LogManager.getLogger(RoomsSortedByRatingAction.class);

    @Override
    public void execute() {
        try {
            List<RoomModel> rooms = FacadeImpl.getInstance().getSortedByRating();
            if(rooms == null) {
                PrintModel.printMessage("Rooms not found.");
            }else {
                PrintModel.printRooms(rooms);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
