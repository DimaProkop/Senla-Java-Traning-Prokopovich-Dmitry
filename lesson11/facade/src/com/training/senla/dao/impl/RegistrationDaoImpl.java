package com.training.senla.dao.impl;

import com.training.senla.dao.BaseModelDao;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.model.Registration;

import java.sql.*;

import static com.training.senla.util.db.ParserResultSet.parseRegistration;

/**
 * Created by prokop on 16.10.16.
 */
public class RegistrationDaoImpl extends BaseModelDao<Registration> implements RegistrationDao {

    public RegistrationDaoImpl() {
    }

    @Override
    public Registration assignParser(ResultSet set) {
        return parseRegistration(set);
    }
}
