package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.repository.GuestModelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by prokop on 13.10.16.
 */
public class GuestModelRepositoryImpl implements GuestModelRepository {

    private List<GuestModel> guests;

    public GuestModelRepositoryImpl() {
    }

    @Override
    public List<ServiceModel> getServicesByDate(Connection connection, GuestModel guestModel) {
        return guests.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_DATE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServiceModel> getServicesByPrice(Connection connection, GuestModel guestModel) {
        return guests.get(guestModel.getId()).getServiceModelList().stream()
                .sorted(Comparator.SERVICE_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuestModel> getSortedByName(Connection connection) {
        Statement statement = null;
        List<GuestModel> guests = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM guest ORDER BY name");
            while (set.next()) {
                guests.add(parseGuest(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }

    @Override
    public int getCount(Connection connection) {
        return guests.size();
    }

    @Override
    public void setGuests(List<GuestModel> guests) {
        this.guests = guests;
    }

    @Override
    public void update(Connection connection, GuestModel entity) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE guest SET name = ?, roomId = ?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getRoomModel().getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GuestModel get(Connection connection, int id) {
        Statement statement = null;
        GuestModel guest = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM guest WHERE id = ");
            builder.append(String.valueOf(id));
            ResultSet set = statement.executeQuery(builder.toString());
            guest = parseGuest(statement, set);
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }

    @Override
    public void set(Connection connection, GuestModel entity) {
        Statement statement = null;
        try {
            StringBuilder builder = new StringBuilder();
            statement = connection.createStatement();
            builder.append("INSERT guest(name) VALUES (");
            builder.append(entity.getName());
            builder.append(")");
            statement.executeUpdate(builder.toString());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GuestModel> getAll(Connection connection) {
        Statement statement = null;
        List<GuestModel> guests = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM guest ORDER BY id");
            while (set.next()) {
                guests.add(parseGuest(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }

    @Override
    public void delete(Connection connection, GuestModel entity) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE * FROM guest WHERE id = ");
            builder.append(String.valueOf(entity.getId()));
            statement.executeUpdate(builder.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private GuestModel parseGuest(Statement statement, ResultSet set) {
        GuestModel guest = null;
        try {
                guest.setId(set.getInt(1));
                guest.setName(set.getString(2));
                if(set.getInt(3) != 0) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("SELECT * FROM room WHERE id = ");
                    builder.append(String.valueOf(set.getInt(3)));
                    ResultSet setRoom = statement.executeQuery(builder.toString());
                    RoomModel room = new RoomModel();
                    room.setId(setRoom.getInt(1));
                    room.setPrice(setRoom.getDouble(2));
                    room.setCapacity(setRoom.getInt(3));
                    room.setStatus(RoomStatus.isExist(setRoom.getString(4)));
                    room.setSection(RoomsSection.isExist(setRoom.getString(5)));
                    room.setRating(setRoom.getInt(6));
                    guest.setRoomModel(room);
                }else {
                    guest.setRoomModel(null);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }
}
