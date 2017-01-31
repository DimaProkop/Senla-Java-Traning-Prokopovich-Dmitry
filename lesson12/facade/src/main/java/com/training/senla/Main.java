package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.sql.*;

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
        final Session session = getSession();
        try {
            session.beginTransaction();

//            Guest guest = session.get(Guest.class, 3);
//            System.out.print(guest.getName());
//            System.out.print(guest.getServiceList().size());

            Service room = session.get(Service.class, 2);
            System.out.print(room.getPrice());
        } finally {
            session.close();
        }
    }

}
