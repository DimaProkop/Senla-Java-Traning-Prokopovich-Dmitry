package com.training.senla.repository;

import com.training.senla.model.BaseModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmitry on 22.1.17.
 */
public interface BaseModelRepository<E extends BaseModel>{
    void update(Connection connection, E entity);
    E get(Connection connection, int id);
    void set(Connection connection, E entity);
    List<E> getAll(Connection connection);
    void delete(Connection connection, E entity);

    default int getCountRow(ResultSet set) throws SQLException {
        int countRow = 0;
        while (set.next()) {
            countRow++;
        }
        return countRow;
    }

}
