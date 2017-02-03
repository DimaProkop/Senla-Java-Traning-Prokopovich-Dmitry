package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.model.Service;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
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
        try {
            session.beginTransaction();

//            DetachedCriteria ownerCriteria = DetachedCriteria.forClass(Registration.class);
//            ownerCriteria.setProjection(Projections.property("roomId"));
//
//
//            @SuppressWarnings("deprecated")
//            Criteria criteria = getSession().createCriteria(Room.class);
//            criteria.add(Property.forName("id").in(ownerCriteria));
//
//            Criteria rowsRoom = getSession().createCriteria(Registration.class);
//            rowsRoom.setProjection(Projections.property("roomId"));
//
//            int max = ((Long) rowsRoom.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//
//            criteria.setFirstResult(max-2).setMaxResults(2);
//
//            List<Room> guests = criteria.list();
//
//            for (Room g: guests) {
//                System.out.println(g.getId());



//            Date date = new Date(118, 2, 3);
//            //List<Room> rooms = session.createQuery("from Room join Registration WHERE Registration.finalDate < :currentDate").setParameter("currentDate", date).list();
//            Criteria criteria = session.createCriteria(Guest.class);
//            List<Guest> guests = session.createQuery("select g from Guest g inner join Registration reg ON reg.guestId = g.id order by reg.finalDate")
//                    .list();

            Criteria criteria = session.createCriteria(Service.class);
            List<Double> prices = criteria.setProjection(Projections.groupProperty("price"))
                    .add(Restrictions.eq("section", ServicesSection.PLACE))
                    .list();


            System.out.print(prices.get(0));

        } finally {
            session.close();
        }

        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();

    }

}
