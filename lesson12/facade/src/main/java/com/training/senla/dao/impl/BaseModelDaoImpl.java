package com.training.senla.dao.impl;

import com.training.senla.dao.BaseModelDao;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.SortType;
import com.training.senla.model.BaseModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.senla.util.connection.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by dmitry on 22.1.17.
 */
public abstract class BaseModelDaoImpl<E extends BaseModel> implements BaseModelDao<E> {

    private static final Logger LOG = LogManager.getLogger(BaseModelDaoImpl.class);

    protected abstract E assignParser(ResultSet set);

    protected abstract String getInsertQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract String getGetByIdQuery();

    protected abstract String getGetAllQuery(SortType type, RoomStatus status);

    protected abstract void setPreparedStatementForInsert(PreparedStatement statement, E entity) throws SQLException;

    protected abstract void setPreparedStatementForUpdate(PreparedStatement statement, E entity) throws SQLException;

    public boolean update(Connection connection, E entity) {
        PreparedStatement statement = null;
        boolean status = false;
        try {
            statement = connection.prepareStatement(getUpdateQuery());
            setPreparedStatementForUpdate(statement, entity);
            int count = statement.executeUpdate();
            status = (count > 0);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            ConnectionManager.getInstance().closeStatement(statement);
        }
        return status;
    }

    public E getById(Connection connection, int id) {
        PreparedStatement statement = null;
        E entity = null;

        try {
            statement = connection.prepareStatement(getGetByIdQuery());
            statement.setInt(1, id);
            entity = assignParser(statement.executeQuery());
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            ConnectionManager.getInstance().closeStatement(statement);
        }
        return entity;
    }

    public void add(Connection connection, E entity) {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(getInsertQuery());
            setPreparedStatementForInsert(statement, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            ConnectionManager.getInstance().closeStatement(statement);
        }
    }

    public List<E> getAll(Connection connection, SortType type, RoomStatus status) {
        List<E> list = new ArrayList<E>();
        PreparedStatement statement = null;
        ResultSet set = null;
        try {
            statement = connection.prepareStatement(getGetAllQuery(type, status));
            set = statement.executeQuery();
            while (set.next()) {
                list.add(assignParser(set));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            ConnectionManager.getInstance().closeStatement(statement);
        }
        return list;
    }

    public void delete(Connection connection, E entity) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(getDeleteQuery());
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            ConnectionManager.getInstance().closeStatement(statement);
        }
    }
}
