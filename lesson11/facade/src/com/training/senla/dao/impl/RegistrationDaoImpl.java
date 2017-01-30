package com.training.senla.dao.impl;

import com.training.senla.dao.RegistrationDao;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.SortType;
import com.training.senla.model.Registration;

import java.sql.*;
import java.text.SimpleDateFormat;

import static com.training.senla.util.db.ParserResultSet.parseRegistration;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationDaoImpl extends BaseModelDaoImpl<Registration> implements RegistrationDao {

    private final String UPDATE_REGISTRATION = "UPDATE guest SET guestId = ?, roomId = ?, startDate = ?, finalDate = ? WHERE id = ?";
    private final String SET_REGISTRATION = "INSERT registration(guestId, roomId, startDate, finalDate) VALUES (?,?,?,?) ";
    private final String GET_REGISTRATION = "SELECT * FROM registration WHERE id = ?";
    private final String DELETE_REGISTRATION = "DELETE * FROM registration WHERE id = ?";
    private final String GET_SORT_REGISTRATION = "SELECT * FROM registration ORDER BY ";
    private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-dd-MM");


    public RegistrationDaoImpl() {
    }

    @Override
    public Registration assignParser(ResultSet set) {
        return parseRegistration(set);
    }

    @Override
    protected String getInsertQuery() {
        return SET_REGISTRATION;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_REGISTRATION;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_REGISTRATION;
    }

    @Override
    protected String getGetByIdQuery() {
        return GET_REGISTRATION;
    }

    @Override
    protected String getGetAllQuery(SortType type, RoomStatus status) {
        return GET_SORT_REGISTRATION + type.toString();
    }

    @Override
    protected void setPreparedStatementForInsert(PreparedStatement statement, Registration entity) throws SQLException {
        statement.setInt(1, entity.getGuestId());
        statement.setInt(2, entity.getRoomId());
        statement.setString(3, formatter.format(entity.getStartDate()));
        statement.setString(4, formatter.format(entity.getFinalDate()));
    }

    @Override
    protected void setPreparedStatementForUpdate(PreparedStatement statement, Registration entity) throws SQLException {
        statement.setInt(1, entity.getGuestId());
        statement.setInt(2, entity.getRoomId());
        statement.setString(3, formatter.format(entity.getStartDate()));
        statement.setString(4, formatter.format(entity.getFinalDate()));
        statement.setInt(5, entity.getId());
    }
}
