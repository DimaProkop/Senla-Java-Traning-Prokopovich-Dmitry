package com.training.senla.dao;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by dmitry on 26.1.17.
 */
public interface BaseModelDao<E> {
    boolean update(PreparedStatement statement);
    E get(PreparedStatement statement);
    void set(PreparedStatement statement);
    List<E> getAll(PreparedStatement statement);
    void delete(PreparedStatement statement);
}
