package com.training.senla.comparators;

import com.training.senla.models.ServiceModel;

import java.util.Comparator;

/**
 * Created by prokop on 13.10.16.
 */
public class ServiceIdComparator implements Comparator<ServiceModel> {
    @Override
    public int compare(ServiceModel serviceModel, ServiceModel t1) {
        return Integer.compare(serviceModel.getServiceId(), t1.getServiceId());
    }
}
