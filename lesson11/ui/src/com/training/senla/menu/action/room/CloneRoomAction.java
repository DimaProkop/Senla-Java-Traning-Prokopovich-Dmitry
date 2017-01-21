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

/**
 * Created by prokop on 2.11.16.
 */
public class CloneRoomAction implements Action {
    private static final Logger LOG = LogManager.getLogger(CloneRoomAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        int roomId = Reader.getInt("Input room id that you want to clone: ");
        DataPacket packet = new DataPacket("getRoom", roomId);
        RoomModel roomExist = (RoomModel) requestHandler.sendRequest(packet);
        if(roomExist == null) {
            PrintModel.printMessage("Room not found.");
        }else {
            packet = new DataPacket("cloneRoom", roomId);
            RoomModel room = (RoomModel) requestHandler.sendRequest(packet);
            String answer = Reader.getString("Do you want to change the number? - Y/N  ");
            if(answer.equals("Y")) {
                room.setPrice(Reader.getDouble("Input price: "));
                room.setCapacity(Reader.getInt("Input capacity: "));
                String strSection = Reader.getString("Input room section: ");
                room.setSection(RoomsSection.isExist(strSection));
                room.setRating(Reader.getInt("Input rating: "));
                packet = new DataPacket("updateRoom", room);
                requestHandler.sendRequest(packet);
                PrintModel.printMessage("Room is successful changed.");
            }else {
                PrintModel.printMessage("Room is clone.");
            }
        }
    }
}
