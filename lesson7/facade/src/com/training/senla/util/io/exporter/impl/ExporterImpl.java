package com.training.senla.util.io.exporter.impl;

import com.training.senla.di.DependencyInjection;
import com.training.senla.manager.impl.EntityManagerImpl;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.service.DataService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ExporterImpl implements Exporter {

    private DataService dataService;
    private EntityManagerImpl entityManagerImpl;

    public ExporterImpl() {
        DependencyInjection injection = new DependencyInjection();
        this.dataService = (DataService) injection.checkInstanceClass("DataService.class");
        this.entityManagerImpl = new EntityManagerImpl();
    }

    @Override
    public void exportGuests(List<GuestModel> guests) {
        entityManagerImpl.analyzeObject(guests, GuestModel.class);
    }

    @Override
    public void exportRegistrations(List<RegistrationModel> registrations) {
        entityManagerImpl.analyzeObject(registrations, RegistrationModel.class);
    }

    @Override
    public void exportRooms(List<RoomModel> rooms) {
        entityManagerImpl.analyzeArray(rooms, RoomModel.class);
    }

    @Override
    public void exportServices(List<ServiceModel> services) {
        entityManagerImpl.analyzeObject(services, ServiceModel.class);
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
