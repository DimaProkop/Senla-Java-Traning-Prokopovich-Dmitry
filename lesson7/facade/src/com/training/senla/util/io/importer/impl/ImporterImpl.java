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
                GuestModel guest = converter.convertStringToGuest(line, template);
                if (guests.contains(guest)) {
                    guests.set(guest.getId(), guest);
                } else {
                    guests.add(guest);
                }
            }
            GuestModelRepository repository = new GuestModelRepositoryImpl(guests);
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
                RoomModel room = converter.convertStringToRoom(line, template);
                if (room.getId() == FacadeImpl.getInstance().getRoom(room.getId()).getId()) {
                    FacadeImpl.getInstance().updateRoom(room);
                } else {
                    rooms.add(room);
                }
            }
            RoomModelRepository repository = new RoomModelRepositoryImpl(rooms);
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
                RegistrationModel registration = converter.convertStringToRegistration(line, template);
                if (registration.getId() == FacadeImpl.getInstance().getRegistration(registration.getId()).getId()) {
                    FacadeImpl.getInstance().updateRegistration(registration);
                } else {
                    registrations.add(registration);
                }
            }
            RegistrationModelRepository repository = new RegistrationModelRepositoryImpl(registrations);
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
                ServiceModel service = converter.convertStringToService(line, template);
                if (service.getId() == FacadeImpl.getInstance().getService(service.getId()).getId()) {
                    FacadeImpl.getInstance().updateService(service);
                } else {
                    services.add(service);
                }
            }
            ServiceModelRepository repository = new ServiceModelRepositoryImpl(services);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
