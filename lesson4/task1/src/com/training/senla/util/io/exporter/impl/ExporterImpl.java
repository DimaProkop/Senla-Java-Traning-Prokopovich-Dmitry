package com.training.senla.util.io.exporter.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.exporter.Exporter;

import com.danco.training.TextFileWorker;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ExporterImpl implements Exporter {

    private TextFileWorker textFileWorker;
    private Converter converter;

    public ExporterImpl(TextFileWorker textFileWorker) {
        this.converter = new ConverterImpl();
        this.textFileWorker = textFileWorker;
    }

    @Override
    public void exportGuests(List<GuestModel> guests) {
        String[] values = new String[guests.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertGuestToString(guests.get(i));
        }
        textFileWorker.writeToFile(values);
    }

    @Override
    public void exportRegistrations(List<RegistrationModel> registrations) {
        String[] values = new String[registrations.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertRegistrationToString(registrations.get(i));
        }
        textFileWorker.writeToFile(values);
    }

    @Override
    public void exportRooms(List<RoomModel> rooms) {
        String[] values = new String[rooms.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertRoomToString(rooms.get(i));
        }
        textFileWorker.writeToFile(values);
    }

    @Override
    public void exportServices(List<ServiceModel> services) {
        String[] values = new String[services.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = converter.convertServiceToString(services.get(i));
        }
        textFileWorker.writeToFile(values);
    }
}
