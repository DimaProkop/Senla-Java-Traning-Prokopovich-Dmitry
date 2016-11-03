package com.training.senla.util.converter.impl;

import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;
import com.training.senla.storage.Storage;
import com.training.senla.util.converter.Converter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 18.10.16.
 */
public class ConverterImpl implements Converter{
    private static final Logger LOG = LogManager.getLogger(ConverterImpl.class);


    public ConverterImpl() {
    }

    @Override
    public List<Object> convertDataToObject() {
        List<Object> data = new ArrayList<>();
        data.add(Storage.guests);
        data.add(Storage.rooms);
        data.add(Storage.services);
        data.add(Storage.registrations);
        return data;
    }

    @Override
    public void convertDataToModel(List<Object> data) {
        try {
            Storage.guests = (List<GuestModel>) data.get(0);
            Storage.rooms = (List<RoomModel>) data.get(1);
            Storage.services = (List<ServiceModel>) data.get(2);
            Storage.registrations = (List<RegistrationModel>) data.get(3);
        }catch (Exception e) {
            LOG.error(e);
        }
    }
}
