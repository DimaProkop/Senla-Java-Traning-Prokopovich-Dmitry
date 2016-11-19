package com.training.senla.util.io.exporter.impl;

import com.training.senla.di.DependencyInjection;
import com.training.senla.manager.EntityManager;
import com.training.senla.manager.impl.EntityManagerImpl;
import com.training.senla.model.*;
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
        this.dataService = (DataService) DependencyInjection.getInstance(DataService.class);
        this.streamService = (StreamService) DependencyInjection.getInstance(StreamService.class);
        this.entityManager = new EntityManagerImpl();
    }

    @Override
    public void exportCollection(List collection, Class clazz) {
        Data data = entityManager.analyzeArray(collection, clazz);
        String fileName = data.getFileName();
        String separator = data.getSeparator();
        int countFields = data.getCountFields();
        List list = data.getData();
        streamService.writeModel(list, fileName, separator, countFields);
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
