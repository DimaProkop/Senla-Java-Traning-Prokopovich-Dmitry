package com.training.senla.menu.action.room;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.RoomModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 24.10.16.
 */
public class NewRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(NewRoomAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            List<Object> objects = new ArrayList<>();
            double price = Reader.getDouble("Input price: ");
            int capacity = Reader.getInt("Input capacity: ");
            String strSection = Reader.getString("Input room section: ");
            RoomsSection section = RoomsSection.isExist(strSection.toUpperCase());
            int rating = Reader.getInt("Input rating: ");
            RoomModel room = new RoomModel(price, capacity, section, rating);
            objects.add(room);
            DataPacket packet = new DataPacket("addRoom", objects);
            requestHandler.sendRequest(packet);
            PrintModel.printMessage("Room created.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
