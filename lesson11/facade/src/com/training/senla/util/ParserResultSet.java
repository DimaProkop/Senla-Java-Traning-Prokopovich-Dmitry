package com.training.senla.util;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 23.1.17.
 */
public class ParserResultSet {

    private static String TOKEN = "'";

    public static Guest parseGuest(ResultSet set) {
        Guest guest = new Guest();
        try {
            guest.setId(set.getInt(1));
            guest.setName(set.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    public static Service parseService(ResultSet set) {
        Service service = new Service();
        try {
            service.setId(set.getInt(1));
            service.setName(set.getString(2));
            service.setPrice(set.getDouble(3));
            service.setSection(ServicesSection.isExist(set.getString(4).toUpperCase()));
            service.setStartDate(set.getDate(5));
            service.setFinalDate(set.getDate(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return service;
    }

    public static Room parseRoom(ResultSet set) {
        Room room = new Room();
        try {
            room.setId(set.getInt(1));
            room.setPrice(set.getDouble(2));
            room.setCapacity(set.getInt(3));
            room.setStatus(RoomStatus.isExist(set.getString(4).toUpperCase()));
            room.setSection(RoomsSection.isExist(set.getString(5).toUpperCase()));
            room.setRating(set.getInt(6));
            room.setGuests(null);
            /**
             * @type = deviant
             */
//            //set guests
//            List<Guest> guests = new ArrayList<>();
//            StringBuilder builder = new StringBuilder();
//            builder.append("SELECT guestId FROM registration WHERE roomId = ");
//            builder.append(TOKEN);
//            builder.append(room.getId());
//            builder.append(TOKEN);
//            set.close();
//            ResultSet setRegistration = statement.executeQuery(builder.toString());
//            if (!setRegistration.next()) {
//                while (setRegistration.next()) {
//
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append("SELECT * FROM guest WHERE id = ");
//                    builder.append(TOKEN);
//                    builder.append(setRegistration.getInt(1));
//                    builder.append(TOKEN);
//                    ResultSet setGuest = statement.executeQuery(stringBuilder.toString());
//                    guests.add(parseGuest(statement, setGuest));
//                    setGuest.close();
//
//                }
//                room.setGuests(guests);
//            }else {
//                room.setGuests(null);
//            }
//            setRegistration.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    public static Registration parseRegistration(ResultSet set) {
        Registration registration = new Registration();
        try {
            registration.setId(set.getInt(1));
            registration.setGuestId(set.getInt(2));
            registration.setRoomId(set.getInt(3));
            registration.setStartDate(set.getDate(4));
            registration.setFinalDate(set.getDate(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registration;
    }
}
