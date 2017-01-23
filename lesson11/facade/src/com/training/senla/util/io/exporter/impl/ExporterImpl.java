package com.training.senla.util.io.exporter.impl;

import com.training.senla.di.DependencyInjection;
import com.training.senla.manager.EntityManager;
import com.training.senla.manager.impl.EntityManagerImpl;
import com.training.senla.model.*;
import com.training.senla.template.WriteTemplate;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.service.StreamService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ExporterImpl implements Exporter {

    private StreamService streamService;
    private EntityManager entityManager;

    public ExporterImpl() {
        this.streamService = (StreamService) DependencyInjection.getInstance(StreamService.class);
        this.entityManager = new EntityManagerImpl();
    }

    @Override
    public void exportCollection(List collection, Class clazz) {
        WriteTemplate template = entityManager.analyzeArray(collection, clazz);
        String fileName = template.getFileName();
        String separator = template.getSeparator();
        int countFields = template.getCountFields();
        List list = template.getData();
        streamService.writeModel(list, fileName, separator, countFields);
    }

    @Override
    public void exportAll(List<GuestModel> guests, List<RegistrationModel> registrations, List<RoomModel> rooms, List<ServiceModel> services) {
        List<Object> data = new ArrayList<>();
        data.add(guests);
        data.add(rooms);
        data.add(services);
        data.add(registrations);
    }
}
