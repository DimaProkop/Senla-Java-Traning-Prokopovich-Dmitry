package com.training.senla.dao.impl;

import com.training.senla.dao.RegistrationDao;
import com.training.senla.model.Registration;

import java.sql.*;
import java.text.SimpleDateFormat;


/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationDaoImpl extends BaseModelDaoImpl<Registration> implements RegistrationDao {

    public RegistrationDaoImpl() {
    }

    @Override
    protected Class assignClass() throws SQLException {
        return Registration.class;
    }
}
