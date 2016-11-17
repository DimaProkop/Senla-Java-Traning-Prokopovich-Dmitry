package com.training.senla.util.io.exporter.impl;

import com.training.senla.di.DependencyInjection;
import com.training.senla.manager.EntityManager;
import com.training.senla.manager.impl.EntityManagerImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.StreamService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ExporterImpl implements Exporter {

    private StreamService streamService;
    private EntityManager entityManager;
    private DataService dataService;

    public ExporterImpl() {
        DependencyInjection injection = new DependencyInjection();
        this.dataService = (DataService) injection.checkInstanceClass("DataService.class");
        this.streamService = (StreamService) injection.checkInstanceClass("StreamService.class");
        this.entityManager = new EntityManagerImpl();
    }

    @Override
    public void exportCollection(List collection, Class clazz) {
        Object[] params = entityManager.analyzeArray(collection, clazz);
        String fileName = String.valueOf(params[0]);
        String separator = String.valueOf(params[1]);
        int countFields = Integer.parseInt(String.valueOf(params[3]));
        streamService.writeModel((List) params[2], fileName, separator, countFields);
    }

    @Override
    public void exportAll(List<GuestModel> guests, List<RegistrationModel> registrations, List<RoomModel> rooms, List<ServiceModel> services) {
        List<Object> data = new ArrayList<>();
        data.add(guests);
        data.add(rooms);
        data.add(services);
        data.add(registrations);
        dataService.saveData(data);
    }
}
