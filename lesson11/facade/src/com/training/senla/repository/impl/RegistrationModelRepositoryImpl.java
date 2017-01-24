package com.training.senla.repository.impl;

import com.training.senla.model.RegistrationModel;
import com.training.senla.repository.RegistrationModelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.training.senla.util.ParserResultSet.parseRegistration;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationModelRepositoryImpl implements RegistrationModelRepository {

    private String TOKEN = "'";
    private String DELIMITER = ", ";

    public RegistrationModelRepositoryImpl() {
    }

    @Override
    public boolean update(Connection connection, RegistrationModel entity) {
        PreparedStatement preparedStatement = null;
        boolean status = false;
        try {
            preparedStatement = connection.prepareStatement("UPDATE registration SET guestId = ?, roomId = ?, startDate = ?, finalDate = ? WHERE id = ");
            preparedStatement.setInt(1, entity.getGuestId());
            preparedStatement.setInt(2, entity.getRoomId());
            preparedStatement.setString(3, entity.getStartDate().toString());
            preparedStatement.setString(4, entity.getFinalDate().toString());
            int count = preparedStatement.executeUpdate();
            status = (count > 0);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public RegistrationModel get(Connection connection, int id) {
        Statement statement = null;
        RegistrationModel registration = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("SELECT * FROM registration WHERE id = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(id));
            builder.append(TOKEN);
            ResultSet set = statement.executeQuery(builder.toString());
            registration = parseRegistration(statement, set);
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registration;
    }

    @Override
    public void set(Connection connection, RegistrationModel entity) {
        Statement statement = null;
        try {
            StringBuilder builder = new StringBuilder();
            statement = connection.createStatement();
            builder.append("INSERT registration(guestId, roomId, startDate, finalDate) VALUES (");
            builder.append(TOKEN);
            builder.append(entity.getGuestId());
            builder.append(TOKEN);
            builder.append(DELIMITER);
            builder.append(TOKEN);
            builder.append(entity.getRoomId());
            builder.append(TOKEN);
            builder.append(DELIMITER);
            builder.append(TOKEN);
            builder.append(entity.getStartDate().toString());
            builder.append(TOKEN);
            builder.append(DELIMITER);
            builder.append(TOKEN);
            builder.append(entity.getFinalDate().toString());
            builder.append(TOKEN);
            builder.append(")");
            statement.executeUpdate(builder.toString());

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RegistrationModel> getAll(Connection connection) {
        Statement statement = null;
        List<RegistrationModel> registrations = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM registration ORDER BY id");
            while (set.next()) {
                registrations.add(parseRegistration(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrations;
    }

    @Override
    public void delete(Connection connection, RegistrationModel entity) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            StringBuilder builder = new StringBuilder();
            builder.append("DELETE * FROM registration WHERE id = ");
            builder.append(TOKEN);
            builder.append(String.valueOf(entity.getId()));
            builder.append(TOKEN);
            statement.executeUpdate(builder.toString());
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
