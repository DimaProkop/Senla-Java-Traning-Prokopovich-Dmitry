package com.training.senla.dao.impl;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by prokop on 13.10.16.
 */
public class GuestDaoImpl extends BaseModelDaoImpl<Guest> implements GuestDao{
    private static final Logger LOG = LogManager.getLogger(GuestDaoImpl.class);


    public GuestDaoImpl() {
        super(Guest.class);
    }


    @Override
    public int getCount(Session session) throws Exception{
        return ((Long) getCriteria(session)
                    .setProjection(Projections.rowCount())
                    .uniqueResult()).intValue();
    }

}
