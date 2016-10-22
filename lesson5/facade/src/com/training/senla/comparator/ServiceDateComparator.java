package com.training.senla.comparator;
import com.training.senla.model.ServiceModel;

import java.util.Comparator;
/**
 * Created by prokop on 15.10.16.
 */
public class ServiceDateComparator implements Comparator<ServiceModel>{
    @Override
    public int compare(ServiceModel serviceModel, ServiceModel t1) {
        return serviceModel.getFinalDate().compareTo(t1.getFinalDate());
    }
}
