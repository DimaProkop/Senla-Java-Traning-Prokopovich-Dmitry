package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.beans.Expression;
import java.sql.*;
import java.util.List;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
//        final Session session = getSession();
//        try {
//            session.beginTransaction();
//
//            Criteria cr = session.createCriteria(Guest.class)
//                    .addOrder(Order.asc())
//
//
//        } finally {
//            session.close();
//        }

        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();
    }

}
