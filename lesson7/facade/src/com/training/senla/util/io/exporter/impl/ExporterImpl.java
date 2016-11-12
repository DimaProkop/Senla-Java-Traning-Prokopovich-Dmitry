package com.training.senla.util.io.exporter.impl;

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

    private Converter converter;
    private DataService dataService;
    private StreamService streamService;

    public ExporterImpl(StreamService streamService) {
        this.converter = new ConverterImpl();
        this.streamService = streamService;
        this.dataService = new DataServiceImpl();
    }

    @Override
    public void exportGuests(List<GuestModel> guests) {
        String[] values = new String[guests.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertGuestToString(guests.get(i));
        }
        streamService.writeModel(values);
    }

    @Override
    public void exportRegistrations(List<RegistrationModel> registrations) {
        String[] values = new String[registrations.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertRegistrationToString(registrations.get(i));
        }
        streamService.writeModel(values);
    }

    @Override
    public void exportRooms(List<RoomModel> rooms) {
        String[] values = new String[rooms.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertRoomToString(rooms.get(i));
        }
        streamService.writeModel(values);
    }

    @Override
    public void exportServices(List<ServiceModel> services) {
        String[] values = new String[services.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertServiceToString(services.get(i));
        }
        streamService.writeModel(values);
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
