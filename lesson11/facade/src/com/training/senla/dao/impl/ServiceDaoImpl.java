package com.training.senla.dao.impl;

import com.training.senla.dao.BaseModelDao;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.Service;
import com.training.senla.dao.ServiceDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.training.senla.util.db.ParserResultSet.parseService;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceDaoImpl extends BaseModelDao<Service> implements ServiceDao {

    public ServiceDaoImpl() {
    }

    @Override
    public Service assignParser(ResultSet set) {
        return parseService(set);
    }

    @Override
    public List<Double> getPriceBySection(Connection connection, ServicesSection section) {
        Statement statement = null;
        List<Double> prices = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT price FROM service ORDER BY section");
            while (set.next()) {
                prices.add(set.getDouble(1));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return prices;
    }
}
