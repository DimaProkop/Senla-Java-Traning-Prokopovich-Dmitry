package com.training.senla.dao;

import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.SortType;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dmitry on 26.1.17.
 */
public interface BaseModelDao<E> {
    void update(Session session, E entity);
    E getById(Session session, int id) throws SQLException;
    void add(Session session, E entity);
    List<E> getAll(Session session, SortType type, RoomStatus status) throws SQLException;
    void delete(Session session, E entity);
}
