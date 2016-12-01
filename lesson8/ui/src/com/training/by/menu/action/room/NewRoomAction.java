package com.training.by.menu.action.room;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.Room;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 24.10.16.
 */
public class NewRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(NewRoomAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            double price = Reader.getDouble("Input price: ");
            int capacity = Reader.getInt("Input capacity: ");
            String strSection = Reader.getString("Input room section: ");
            RoomsSection section = RoomsSection.isExist(strSection.toUpperCase());
            int rating = Reader.getInt("Input rating: ");
            Room room = new Room(price, capacity, section, rating);
            DataPacket packet = new DataPacket("addRoom", room);
            requestHandler.sendRequest(packet);
            PrintModel.printMessage("Room created.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
