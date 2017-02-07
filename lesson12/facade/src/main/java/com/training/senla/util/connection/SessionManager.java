package com.training.senla.util.connection;

import com.training.senla.ClassSetting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

/**
 * Created by dmitry on 22.1.17.
 */
public class SessionManager {

    private static final Logger LOG = LogManager.getLogger(SessionManager.class);


    private static SessionManager sessionManager;
    private static SessionFactory sessionFactory;


    public static SessionManager getInstance() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public Session getSession() {

        Configuration configuration = new Configuration();
        configuration.configure();

        sessionFactory = configuration.buildSessionFactory();

        return sessionFactory.openSession();
    }

    public void closeSession(Session session) {
        try {
            if (session != null) {
                session.close();
            }
        } catch (HibernateException e) {
            LOG.error(e.getMessage());
        }
    }
}
