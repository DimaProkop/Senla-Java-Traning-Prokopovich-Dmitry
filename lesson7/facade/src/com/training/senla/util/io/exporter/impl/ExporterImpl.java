package com.training.senla.util.io.exporter.impl;

import com.training.senla.manager.EntityManager;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.StreamService;
import com.training.senla.util.service.impl.DataServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ExporterImpl implements Exporter {

    private DataService dataService;
    private EntityManager entityManager;

    public ExporterImpl() {
        this.dataService = new DataServiceImpl();
        this.entityManager = new EntityManager();
    }

    @Override
    public void exportGuests(List<GuestModel> guests) {
        entityManager.analyzeData(guests, GuestModel.class);
    }

    @Override
    public void exportRegistrations(List<RegistrationModel> registrations) {
        entityManager.analyzeData(registrations, RegistrationModel.class);
    }

    @Override
    public void exportRooms(List<RoomModel> rooms) {
        entityManager.analyzeData(rooms, RoomModel.class);
    }

    @Override
    public void exportServices(List<ServiceModel> services) {
        entityManager.analyzeData(services, ServiceModel.class);
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
