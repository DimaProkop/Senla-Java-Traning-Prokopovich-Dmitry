package com.training.senla.util.io.exporter.impl;

import com.training.senla.storage.Storage;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.StreamService;
import com.training.senla.util.service.impl.DataServiceImpl;

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
    public void exportGuests() {
        String[] values = new String[Storage.guests.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertGuestToString(Storage.guests.get(i));
        }
        streamService.writeModel(values);
    }

    @Override
    public void exportRegistrations() {
        String[] values = new String[Storage.registrations.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertRegistrationToString(Storage.registrations.get(i));
        }
        streamService.writeModel(values);
    }

    @Override
    public void exportRooms() {
        String[] values = new String[Storage.rooms.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertRoomToString(Storage.rooms.get(i));
        }
        streamService.writeModel(values);
    }

    @Override
    public void exportServices() {
        String[] values = new String[Storage.services.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertServiceToString(Storage.services.get(i));
        }
        streamService.writeModel(values);
    }

    @Override
    public void exportAll() {
        List<Object> data = converter.convertDataToObject();
        dataService.saveData(data);
    }
}
