package com.training.by.menu.action.room;

import com.training.by.menu.action.Action;
import com.training.senla.enums.RoomsSection;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.RoomModel;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 24.10.16.
 */
public class NewRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(NewRoomAction.class);

    @Override
    public void execute() {
        try {
            double price = Reader.getDouble("Input price: ");
            int capacity = Reader.getInt("Input capacity: ");
            String strSection = Reader.getString("Input room section: ");
            RoomsSection section = RoomsSection.isExist(strSection.toUpperCase());
            int rating = Reader.getInt("Input rating: ");
            RoomModel room = new RoomModel(price, capacity, section, rating);
            FacadeImpl.getInstance().addRoom(room);
            PrintModel.printMessage("Room created.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
