package com.training.by.menu.action.room;

import com.training.senla.facade.impl.FacadeImpl;
import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class CountFreeRoomsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(CountFreeRoomsAction.class);

    @Override
    public void execute() {
        try {
            int count = FacadeImpl.getInstance().getCountFreeRooms();
            if(count == 0) {
                PrintModel.printMessage("No free rooms");
            }else {
                PrintModel.printMessage(String.valueOf(count));
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
