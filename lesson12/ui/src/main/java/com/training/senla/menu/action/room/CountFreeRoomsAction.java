package com.training.senla.menu.action.room;

import com.training.senla.menu.action.Action;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.print.PrintModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class CountFreeRoomsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(CountFreeRoomsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getCountFreeRooms", null);
            int count = (int) requestHandler.sendRequest(packet);
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
