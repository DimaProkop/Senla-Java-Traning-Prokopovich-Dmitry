package com.training.senla.dao;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.SortType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by dmitry on 26.1.17.
 */
public interface BaseModelDao<E> {
    boolean update(Connection connection, E entity);
    E getById(Connection connection, int id);
    void add(Connection connection, E entity);
    List<E> getAll(Connection connection, SortType type, RoomStatus status);
    void delete(Connection connection, E entity);
}
