package com.training.senla.repository.impl;

import com.training.senla.ClassSetting;
import com.training.senla.model.RegistrationModel;
import com.training.senla.repository.RegistrationModelRepository;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.training.senla.util.ParserResultSet.parseRegistration;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationModelRepositoryImpl implements RegistrationModelRepository {

    private String TOKEN = "'";

    public RegistrationModelRepositoryImpl() {
    }

    @Override
    public void update(Connection connection, RegistrationModel entity) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE registration SET guestId = ?, roomId = ?, section = ?, startDate = ?, finalDate = ? WHERE id = ");
            preparedStatement.setInt(1, entity.getGuestId());
            preparedStatement.setInt(2, entity.getRoomId());
            preparedStatement.setString(3, entity.getStartDate().toString());
            preparedStatement.setString(4, entity.getFinalDate().toString());
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    }

    @Override
    public List<RegistrationModel> getAll(Connection connection) {
        return null;
    }

    @Override
    public void delete(Connection connection, RegistrationModel entity) {

    }
}
