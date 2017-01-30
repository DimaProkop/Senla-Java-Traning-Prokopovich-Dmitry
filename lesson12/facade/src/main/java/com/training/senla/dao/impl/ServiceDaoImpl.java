package com.training.senla.dao.impl;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.ServicesSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Service;
import com.training.senla.dao.ServiceDao;
import com.training.senla.util.connection.ConnectionManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.training.senla.util.db.ParserResultSet.parseService;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceDaoImpl extends BaseModelDaoImpl<Service> implements ServiceDao {

    private final String UPDATE_SERVICE = "UPDATE guest SET name = ?, price = ?, section = ?, startDate = ?, finalDate = ? WHERE id = ?";
    private final String SET_SERVICE = "INSERT service(name, price, section, startDate, finalDate) VALUES (?,?,?,?,?) ";
    private final String GET_SERVICE = "SELECT * FROM service WHERE id = ?";
    private final String DELETE_SERVICE = "DELETE * FROM service WHERE id = ?";
    private final String GET_SORT_SERVICE = "SELECT * FROM service ORDER BY ";
    private final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-dd-MM");

    public ServiceDaoImpl() {
    }

    @Override
    public Service assignParser(ResultSet set) {
        return parseService(set);
    }

    @Override
    protected String getInsertQuery() {
        return SET_SERVICE;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_SERVICE;
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_SERVICE;
    }

    @Override
    protected String getGetByIdQuery() {
        return GET_SERVICE;
    }

    @Override
    protected String getGetAllQuery(SortType type, RoomStatus status) {
        return GET_SORT_SERVICE;
    }

    @Override
    protected void setPreparedStatementForInsert(PreparedStatement statement, Service entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setDouble(2, entity.getPrice());
        statement.setString(3, entity.getSection().toString());
        statement.setString(4, formatter.format(entity.getStartDate()));
        statement.setString(5, formatter.format(entity.getFinalDate()));
    }

    @Override
    protected void setPreparedStatementForUpdate(PreparedStatement statement, Service entity) throws SQLException {
        statement.setString(1, entity.getName());
        statement.setDouble(2, entity.getPrice());
        statement.setString(3, entity.getSection().toString());
        statement.setString(4, formatter.format(entity.getStartDate()));
        statement.setString(5, formatter.format(entity.getFinalDate()));
        statement.setInt(6, entity.getId());
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
            ConnectionManager.getInstance().closeStatement(statement);
        }

        return prices;
    }
}
