package com.training.senla.dao.impl;

import com.training.senla.dao.BaseModelDao;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.SortType;
import com.training.senla.model.BaseModel;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Created by dmitry on 22.1.17.
 */
public abstract class BaseModelDaoImpl<E extends BaseModel> implements BaseModelDao<E> {

    private static final Logger LOG = LogManager.getLogger(BaseModelDaoImpl.class);

    protected abstract Class assignClass() throws SQLException;

    public void update(Session session, E entity) {
        session.update(entity);
    }

    public E getById(Session session, int id) throws SQLException {
        return (E) session.get(assignClass(), id);
    }

    public void add(Session session, E entity) {
        session.save(entity);
    }

    public List<E> getAll(Session session, SortType type, RoomStatus status) throws SQLException {
        if(type == null) {
            type = SortType.id;
        }
        Criteria criteria = session.createCriteria(assignClass())
                .addOrder(Order.asc(type.toString()));
        if(status != null) {
            criteria.add(Restrictions.like("status", status));
        }

        return criteria.list();
    }

    public void delete(Session session, E entity) {
        session.delete(entity);
    }
}
