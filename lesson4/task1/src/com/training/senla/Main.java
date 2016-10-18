package com.training.senla;

import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.ServiceModel;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by prokop on 12.10.16.
 */


public class Main {

    public static void main(String[] args) throws IOException {

        Facade facade = new FacadeImpl();

        ServiceModel serviceModel = new ServiceModel("cyka", 54.34, ServicesSection.PLACE, LocalDate.now(), LocalDate.of(2017, 6, 6));
        facade.setService(serviceModel);

        for(ServiceModel service : facade.getAllServices()) {
            System.out.println(service.getId());
        }


    }
}
