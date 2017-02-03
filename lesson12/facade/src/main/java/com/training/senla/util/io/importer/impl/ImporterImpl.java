package com.training.senla.util.io.importer.impl;

import com.training.senla.ClassSetting;
import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;
import com.training.senla.manager.EntityManager;
import com.training.senla.manager.impl.EntityManagerImpl;
import com.training.senla.model.*;
import com.training.senla.template.ReadTemplate;
import com.training.senla.util.converter.Converter;
import com.training.senla.util.io.importer.Importer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by prokop on 16.10.16.
 */
public class ImporterImpl implements Importer {
    private static final Logger LOG = LogManager.getLogger(ImporterImpl.class);
    private static final String path = ClassSetting.getProps().getPathToFolderEntity();

    private Converter converter;
    private EntityManager manager;
    private Facade facade;

    public ImporterImpl() {
        this.facade = (Facade) DependencyInjection.getInstance(Facade.class); 
        this.converter = (Converter) DependencyInjection.getInstance(Converter.class);
        this.manager = new EntityManagerImpl();
    }

    @Override
    public void importGuests(List<Guest> guests) {
        ReadTemplate template = manager.analyzeObject(Guest.class);
        try (BufferedReader br = new BufferedReader(new FileReader(path + template.getFileName()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Guest currentGuest = converter.convertStringToGuest(line, template);
                Guest guest = facade.getGuest(currentGuest.getId());
                if (guest != null) {
                    facade.updateGuest(currentGuest);
                } else {
                    facade.addGuest(currentGuest);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importRooms(List<Room> rooms) {
        ReadTemplate template = manager.analyzeObject(Room.class);
        try (BufferedReader br = new BufferedReader(new FileReader(path + template.getFileName()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Room currentRoom = converter.convertStringToRoom(line, template);
                Room room = facade.getRoom(currentRoom.getId());
                if (room != null) {
                    facade.updateRoom(currentRoom);
                } else {
                    facade.addRoom(currentRoom);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importRegistrations(List<Registration> registrations) {
        ReadTemplate template = manager.analyzeObject(Registration.class);
        try (BufferedReader br = new BufferedReader(new FileReader(path + template.getFileName()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Registration currentRegistration = converter.convertStringToRegistration(line, template);
                Registration registration = facade.getRegistration(currentRegistration.getId());
                if (registration != null) {
                    facade.updateRegistration(currentRegistration);
                } else {
                    facade.addRegistration(currentRegistration);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    @Override
    public void importServices(List<Service> services) {
        ReadTemplate template = manager.analyzeObject(Service.class);
        try (BufferedReader br = new BufferedReader(new FileReader(path + template.getFileName()))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                Service currentService = converter.convertStringToService(line, template);
                Service service = facade.getService(currentService.getId());
                if (service != null) {
                    facade.updateService(currentService);
                } else {
                    facade.addService(currentService);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
