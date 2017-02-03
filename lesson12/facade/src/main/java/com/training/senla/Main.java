package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.model.Registration;
import com.training.senla.model.Service;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;

import java.util.Date;
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
        final Session session = getSession();
//        try {
//            session.beginTransaction();
//
//            Criteria criteria = session.createCriteria(Registration.class);
//            Criterion guestId = Restrictions.eq("guestId", guest.getId());
//            Criterion roomId = Restrictions.eq("roomId", room.getId());
//            Criteria criteria = session.createCriteria(Registration.class);
//            criteria.add(Expression.between("orderTime", today, tomorrow));
//            totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
//        } finally {
//            session.close();
//        }

        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();

    }

}
