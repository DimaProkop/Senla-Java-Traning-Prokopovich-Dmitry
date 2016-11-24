package com.training.senla.util.io.importer.impl;

import com.training.senla.ClassSetting;
import com.training.senla.Props;
import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.manager.EntityManager;
import com.training.senla.manager.impl.EntityManagerImpl;
import com.training.senla.model.*;
import com.training.senla.repository.GuestModelRepository;
import com.training.senla.repository.RegistrationModelRepository;
import com.training.senla.repository.RoomModelRepository;
import com.training.senla.repository.ServiceModelRepository;
import com.training.senla.repository.impl.GuestModelRepositoryImpl;
import com.training.senla.repository.impl.RegistrationModelRepositoryImpl;
import com.training.senla.repository.impl.RoomModelRepositoryImpl;
import com.training.senla.repository.impl.ServiceModelRepositoryImpl;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.converter.impl.ConverterImpl;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.service.DataService;
import com.training.senla.util.service.impl.DataServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by prokop on 16.10.16.
 */
public class ImporterImpl implements Importer {
    private static final Logger LOG = LogManager.getLogger(ImporterImpl.class);
    private static final String path = ClassSetting.getProps().getPathToFolderEntity();

    private Converter converter;
    private EntityManager manager;

    public ImporterImpl() {
        this.converter = (Converter) DependencyInjection.getInstance(Converter.class);
        this.manager = new EntityManagerImpl();
    }

    @Override
    public void importGuests(List<GuestModel> guests) {
        ReadTemplate template = manager.analyzeObject(GuestModel.class);
        try (BufferedReader br = new BufferedReader(new FileReader(path + template.getFileName()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                GuestModel currentGuest = converter.convertStringToGuest(line, template);
                GuestModel guest = FacadeImpl.getInstance().getGuest(currentGuest.getId());
                if (guest != null) {
                    FacadeImpl.getInstance().updateGuest(currentGuest);
                } else {
                    FacadeImpl.getInstance().addGuest(currentGuest);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importRooms(List<RoomModel> rooms) {
        ReadTemplate template = manager.analyzeObject(RoomModel.class);
        try (BufferedReader br = new BufferedReader(new FileReader(path + template.getFileName()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                RoomModel currentRoom = converter.convertStringToRoom(line, template);
                RoomModel room = FacadeImpl.getInstance().getRoom(currentRoom.getId());
                if (room != null) {
                    FacadeImpl.getInstance().updateRoom(currentRoom);
                } else {
                    FacadeImpl.getInstance().addRoom(currentRoom);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importRegistrations(List<RegistrationModel> registrations) {
        ReadTemplate template = manager.analyzeObject(RegistrationModel.class);
        try (BufferedReader br = new BufferedReader(new FileReader(path + template.getFileName()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                RegistrationModel currentRegistration = converter.convertStringToRegistration(line, template);
                RegistrationModel registration = FacadeImpl.getInstance().getRegistration(currentRegistration.getId());
                if (registration != null) {
                    FacadeImpl.getInstance().updateRegistration(currentRegistration);
                } else {
                    FacadeImpl.getInstance().addRegistration(currentRegistration);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importServices(List<ServiceModel> services) {
        ReadTemplate template = manager.analyzeObject(ServiceModel.class);
        try (BufferedReader br = new BufferedReader(new FileReader(path + template.getFileName()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                ServiceModel currentService = converter.convertStringToService(line, template);
                ServiceModel service = FacadeImpl.getInstance().getService(currentService.getId());
                if (service != null) {
                    FacadeImpl.getInstance().updateService(currentService);
                } else {
                    FacadeImpl.getInstance().addService(currentService);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
