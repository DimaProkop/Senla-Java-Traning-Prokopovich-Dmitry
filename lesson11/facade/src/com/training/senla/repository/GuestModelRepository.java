package com.training.senla.repository;

import com.training.senla.model.GuestModel;
import com.training.senla.model.ServiceModel;

import java.sql.Connection;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public interface GuestModelRepository extends BaseModelRepository<GuestModel>{

    List<ServiceModel> getServicesByPrice(Connection connection, GuestModel guestModel);

    List<ServiceModel> getServicesByDate(Connection connection, GuestModel guestModel);

    List<GuestModel> getSortedByName(Connection connection);

    int getCount(Connection connection);
}
