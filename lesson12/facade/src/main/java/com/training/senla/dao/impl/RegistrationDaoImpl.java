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

    private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-dd-MM");


    public RegistrationDaoImpl() {
    }

    @Override
    protected Class assignClass() throws SQLException {
        return Registration.class;
    }
}
