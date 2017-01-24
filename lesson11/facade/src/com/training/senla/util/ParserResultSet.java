package com.training.senla.util;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

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

    public static GuestModel parseGuest(Statement statement, ResultSet set) {
        GuestModel guest = new GuestModel();
        try {
            guest.setId(set.getInt(1));
            guest.setName(set.getString(2));
            int roomId = set.getInt(3);
            if (roomId != 0) {
                //set Room
                StringBuilder builder = new StringBuilder();
                builder.append("SELECT * FROM room WHERE id = ");
                builder.append(TOKEN);
                builder.append(roomId);
                builder.append(TOKEN);
                ResultSet setRoom = statement.executeQuery(builder.toString());
                guest.setRoomModel(parseRoom(statement, setRoom));
                setRoom.close();

            } else {
                guest.setRoomModel(null);
            }

            //set Services
            List<ServiceModel> services = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT * FROM service WHERE guestId = ");
            stringBuilder.append(TOKEN);
            stringBuilder.append(guest.getId());
            stringBuilder.append(TOKEN);
            ResultSet setService = statement.executeQuery(stringBuilder.toString());
            while (setService.next()) {
                services.add(parseService(statement, setService));
            }
            guest.setServiceModelList(services);
            setService.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    public static ServiceModel parseService(Statement statement, ResultSet set) {
        ServiceModel service = new ServiceModel();
        try {
            service.setId(set.getInt(1));
            service.setName(set.getString(2));
            service.setPrice(set.getDouble(3));
            service.setSection(ServicesSection.isExist(set.getString(4)));
            service.setStartDate(set.getDate(5));
            service.setFinalDate(set.getDate(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return service;
    }

    public static RoomModel parseRoom(Statement statement, ResultSet set) {
        RoomModel room = new RoomModel();
        try {
            room.setId(set.getInt(1));
            room.setPrice(set.getDouble(2));
            room.setCapacity(set.getInt(3));
            room.setStatus(RoomStatus.isExist(set.getString(4)));
            room.setSection(RoomsSection.isExist(set.getString(5)));
            room.setRating(set.getInt(6));

            //set guests
            List<GuestModel> guests = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT guestId FROM registration WHERE roomId = ");
            builder.append(TOKEN);
            builder.append(room.getId());
            builder.append(TOKEN);
            ResultSet setRegistration = statement.executeQuery(builder.toString());
            if (!setRegistration.wasNull()) {
                while (setRegistration.next()) {

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("SELECT * FROM guest WHERE id = ");
                    builder.append(TOKEN);
                    builder.append(setRegistration.getInt(1));
                    builder.append(TOKEN);
                    ResultSet setGuest = statement.executeQuery(stringBuilder.toString());
                    guests.add(parseGuest(statement, setGuest));
                    setGuest.close();

                }
                room.setGuests(guests);
            }else {
                room.setGuests(null);
            }
            setRegistration.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    public static RegistrationModel parseRegistration(Statement statement, ResultSet set) {
        RegistrationModel registration = new RegistrationModel();
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
