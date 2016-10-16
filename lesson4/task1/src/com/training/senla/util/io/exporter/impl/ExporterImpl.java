package com.training.senla.util.io.exporter.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.util.io.exporter.Exporter;

import com.danco.training.TextFileWorker;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ExporterImpl implements Exporter {

    private final static String FILE_PATH = "/home/prokop/Senla-Java-Traning-Prokopovich-Dmitry/lesson4/task1/resource/main.txt";
    private TextFileWorker textFileWorker = new TextFileWorker(FILE_PATH);

    @Override
    public void exportGuests(List<GuestModel> guests) {
        String[] values = new String[guests.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = ConvertGuestToString(guests.get(i));
        }
        textFileWorker.writeToFile(values);
    }

    @Override
    public void exportRegistrations(List<RegistrationModel> registrations) {
        String[] values = new String[registrations.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = ConvertRegistrationToString(registrations.get(i));
        }
        textFileWorker.writeToFile(values);
    }

    @Override
    public void exportRooms(List<RoomModel> rooms) {
        String[] values = new String[rooms.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = ConvertRoomToString(rooms.get(i));
        }
        textFileWorker.writeToFile(values);
    }

    @Override
    public void exportServices(List<ServiceModel> services) {
        String[] values = new String[services.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = ConvertServiceToString(services.get(i));
        }
        textFileWorker.writeToFile(values);
    }

    private String ConvertGuestToString(GuestModel guestModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(guestModel.getGuestId()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getName()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getStartDate()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getFinalDate()));
        builder.append(";");
        builder.append(String.valueOf(guestModel.getRoomModel().getRoomId()));
        builder.append(";");
        for (ServiceModel serviceModel : guestModel.getServiceModelList()) {
            builder.append(serviceModel.getServiceId());
            builder.append(",");
        }
        builder.append(";");
        return builder.toString();
    }

    private String ConvertRoomToString(RoomModel roomModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(roomModel.getRoomId()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getPrice()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getCapacity()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getStatus()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getSection()));
        builder.append(";");
        builder.append(String.valueOf(roomModel.getRating()));
        builder.append(";");
        for (GuestModel guestModel : roomModel.getGuests()) {
            builder.append(guestModel.getGuestId());
            builder.append(",");
        }
        builder.append(";");
        return builder.toString();
    }

    private String ConvertServiceToString(ServiceModel serviceModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(serviceModel.getServiceId()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getName()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getPrice()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getSection()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getStartDate()));
        builder.append(";");
        builder.append(String.valueOf(serviceModel.getFinalDate()));
        builder.append(";");
        return builder.toString();
    }

    private String ConvertRegistrationToString(RegistrationModel registrationModel) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(registrationModel.getRegistrationId()));
        builder.append(";");
        builder.append(String.valueOf(registrationModel.getGuestId()));
        builder.append(";");
        builder.append(String.valueOf(registrationModel.getRoomId()));
        builder.append(";");
        builder.append(String.valueOf(registrationModel.getStartDate()));
        builder.append(";");
        builder.append(String.valueOf(registrationModel.getFinalDate()));
        builder.append(";");
        return builder.toString();
    }
}
