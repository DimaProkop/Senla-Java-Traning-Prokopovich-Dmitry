package com.training.senla.dao.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.ServicesSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Service;
import com.training.senla.dao.ServiceDao;
import com.training.senla.util.connection.SessionManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.training.senla.util.db.ParserResultSet.parseService;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceDaoImpl extends BaseModelDaoImpl<Service> implements ServiceDao {

    private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-dd-MM");

    public ServiceDaoImpl() {
    }


    @Override
    public List<Double> getPriceBySection(Connection connection, ServicesSection section) {
        PreparedStatement statement = null;
        List<Double> prices = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT price FROM service ORDER BY ?");
            statement.setString(1, section.toString());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                prices.add(set.getDouble(1));
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

        return prices;
    }

    @Override
    protected Class assignClass() throws SQLException {
        return Service.class;
    }
}
