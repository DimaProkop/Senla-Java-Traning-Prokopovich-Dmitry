package com.training.senla.repository.impl;

import com.training.senla.comparator.Comparator;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.RoomsSection;
import com.training.senla.model.RoomModel;
import com.training.senla.repository.RoomModelRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomModelRepositoryImpl implements RoomModelRepository {

    private List<RoomModel> rooms;

    public RoomModelRepositoryImpl() {
    }

    private int getRoomIndexById(int id) {
        for (int i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<RoomModel> getSortedByPrice() {
        return rooms.stream()
                .sorted(Comparator.ROOM_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByCapacity() {
        return rooms.stream()
                .sorted(Comparator.ROOM_CAPACITRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByRating() {
        return rooms.stream()
                .sorted(Comparator.ROOM_RATING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getAll(RoomStatus status) {
        return rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_ID_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByPrice(RoomStatus status) {
        return rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_PRICE_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByCapacity(RoomStatus status) {
        return rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_CAPACITRY_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomModel> getSortedByRating(RoomStatus status) {
        return rooms.stream()
                .filter(x -> x.getStatus().equals(status))
                .sorted(Comparator.ROOM_RATING_COMPARATOR)
                .collect(Collectors.toList());
    }

    @Override
    public int getCountFreeRooms() {
                return rooms.stream()
                        .filter(x -> x.getStatus().equals(RoomStatus.FREE))
                        .collect(Collectors.toList()).size();
    }

    @Override
    public List<RoomModel> getLatestGuests(int count) {
        return rooms;
    }

    @Override
    public List<Double> getPriceBySection(RoomsSection section) {
        return null;
    }

    @Override
    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }

    @Override
    public void update(Connection connection, RoomModel entity) {

    }

    @Override
    public RoomModel get(Connection connection, int id) {
        return null;
    }

    @Override
    public void set(Connection connection, RoomModel entity) {

    }

    @Override
    public List<RoomModel> getAll(Connection connection) {
        Statement statement = null;
        List<RoomModel> rooms = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM guest ORDER BY id");
            while (set.next()) {
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    @Override
    public void delete(Connection connection, RoomModel entity) {

    }

    private RoomModel parseRoom(Statement statement, ResultSet set) {
        RoomModel room = null;
        try {
            room.setId(set.getInt(1));
            room.setPrice(set.getDouble(2));
            room.setCapacity(set.getInt(3));
            room.setStatus(RoomStatus.isExist(set.getString(4)));
            room.setSection(RoomsSection.isExist(set.getString(5)));
            room.setRating(set.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
}
