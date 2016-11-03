package com.training.senla.util.io.exporter.impl;

import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.impl.DataServiceImpl;

import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ExporterImpl implements Exporter {

    private DataService dataService;
    private Converter converter;

    public ExporterImpl() {
        this.dataService = new DataServiceImpl();
        this.converter = new ConverterImpl();
    }

    @Override
    public void exportGuests() {

    }

    @Override
    public void exportRegistrations() {

    }

    @Override
    public void exportRooms() {

    }

    @Override
    public void exportServices() {

    }

    @Override
    public void exportAll() {
        List<Object> data = converter.convertDataToObject();
        dataService.saveData(data);
    }
}
