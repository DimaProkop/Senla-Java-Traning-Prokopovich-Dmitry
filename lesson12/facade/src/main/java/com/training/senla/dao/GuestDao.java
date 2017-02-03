package com.training.senla.dao;

import com.training.senla.enums.SortType;
import com.training.senla.model.Guest;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface GuestDao extends BaseModelDao<Guest>{

    int getCount(Session session);
}
