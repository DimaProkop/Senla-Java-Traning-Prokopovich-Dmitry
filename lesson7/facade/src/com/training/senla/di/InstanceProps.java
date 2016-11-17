package com.training.senla.di;

import com.training.senla.Props;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by dmitry on 17.11.16.
 */
public class InstanceProps {
    private String facade;
    private String guestModelService;
    private String roomModelService;
    private String serviceModelService;
    private String registrationModelService;
    private String guestModelRepository;
    private String roomModelRepository;
    private String serviceModelRepository;
    private String registrationModelRepository;
    private String converter;
    private String exporter;
    private String importer;
    private String dataService;
    private String streamService;

    public InstanceProps() {
        Properties properties = new Properties();
        String s = Props.getPathToFolderEntity();
        try (FileInputStream stream = new FileInputStream(Props.getPathToInstanceFile())) {
            properties.load(stream);
            facade = properties.getProperty("Facade.class");
            guestModelService = properties.getProperty("GuestModelService.class");
            roomModelService = properties.getProperty("RoomModelService.class");
            registrationModelService = properties.getProperty("RegistrationModelService.class");
            serviceModelService = properties.getProperty("ServiceModelService.class");
            guestModelRepository = properties.getProperty("GuestModelRepository.class");
            roomModelRepository = properties.getProperty("RoomModelRepository.class");
            registrationModelRepository = properties.getProperty("RegistrationModelRepository.class");
            serviceModelRepository = properties.getProperty("ServiceModelRepository.class");
            converter = properties.getProperty("Converter.class");
            exporter = properties.getProperty("Exporter.class");
            importer = properties.getProperty("Importer.class");
            dataService = properties.getProperty("DataService.class");
            streamService = properties.getProperty("StreamService.class");

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFacade() {
        return facade;
    }

    public String getGuestModelService() {
        return guestModelService;
    }

    public String getRoomModelService() {
        return roomModelService;
    }

    public String getServiceModelService() {
        return serviceModelService;
    }

    public String getRegistrationModelService() {
        return registrationModelService;
    }

    public String getGuestModelRepository() {
        return guestModelRepository;
    }

    public String getRoomModelRepository() {
        return roomModelRepository;
    }

    public String getServiceModelRepository() {
        return serviceModelRepository;
    }

    public String getRegistrationModelRepository() {
        return registrationModelRepository;
    }

    public String getConverter() {
        return converter;
    }

    public String getExporter() {
        return exporter;
    }

    public String getImporter() {
        return importer;
    }

    public String getDataService() {
        return dataService;
    }

    public String getStreamService() {
        return streamService;
    }
}
