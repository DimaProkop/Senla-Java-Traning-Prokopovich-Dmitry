package com.training.senla.dao.impl;

import com.training.senla.enums.ServicesSection;
import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Service;
import com.training.senla.dao.ServiceDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceDaoImpl extends BaseModelDaoImpl<Service> implements ServiceDao {

    private static final Logger LOG = LogManager.getLogger(ServiceDaoImpl.class);

    public ServiceDaoImpl() {
        super(Service.class);
    }


    @Override
    public List<Service> getServices(Session session, Guest guest, SortType type) throws Exception{
        List<Service> services = new ArrayList<>();
            Criteria criteria = getCriteria(session);
            services = criteria.add(Restrictions.eq("guestId", guest.getId()))
                    .addOrder(Order.asc(type.toString())).list();

        return services;
    }


    @Override
    public List<Double> getPriceBySection(Session session, ServicesSection section) throws Exception{
        List<Double> prices = new ArrayList<>();
            Criteria criteria = getCriteria(session);
            prices = criteria.setProjection(Projections.groupProperty("price"))
                    .add(Restrictions.eq("section", section))
                    .list();
        return prices;
    }

}
