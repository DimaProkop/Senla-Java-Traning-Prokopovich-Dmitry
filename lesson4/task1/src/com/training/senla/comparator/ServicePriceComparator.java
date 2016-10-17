package com.training.senla.comparator;
import com.training.senla.model.ServiceModel;

import java.util.Comparator;
/**
 * Created by prokop on 15.10.16.
 */
public class ServicePriceComparator implements Comparator<ServiceModel>{
    @Override
    public int compare(ServiceModel serviceModel, ServiceModel t1) {
        return Double.compare(serviceModel.getPrice(), t1.getPrice());
    }
}
