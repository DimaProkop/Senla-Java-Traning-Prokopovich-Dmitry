package com.training.senla.dao.impl;

import com.training.senla.dao.BaseModelDao;
import com.training.senla.model.BaseModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by dmitry on 22.1.17.
 */
public abstract class BaseModelDaoImpl<E extends BaseModel> implements BaseModelDao<E>{

    private static final Logger LOG = LogManager.getLogger(BaseModelDaoImpl.class);

    public abstract E assignParser(ResultSet set);

    private boolean actionQuery(PreparedStatement statement) {
        boolean status = false;
        try {
            int count = statement.executeUpdate();
            status = (count > 0);
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException sql) {
                LOG.error(sql.getMessage());
            }
        }
        return status;
    }

    public boolean update(PreparedStatement statement) {
        return actionQuery(statement);
    }

    public E get(PreparedStatement statement) {
        E entity = null;
        ResultSet set;
        try {
            set = statement.executeQuery();
            while (set.next()) {
                entity = assignParser(set);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException sql) {
                LOG.error(sql.getMessage());
            }
        }
        return entity;
    }

    public void set(PreparedStatement statement) {
        actionQuery(statement);
    }

    public List<E> getAll(PreparedStatement statement) {
        List<E> list = new ArrayList<E>();
        ResultSet set;
        try {
            set = statement.executeQuery();
            while (set.next()) {
                list.add(assignParser(set));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException sql) {
                LOG.error(sql.getMessage());
            }
        }
        return list;
    }

    public void delete(PreparedStatement statement) {
        actionQuery(statement);
    }
}
