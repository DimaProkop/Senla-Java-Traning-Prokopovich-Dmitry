package com.training.senla.facade.impl;

import com.training.senla.ClassSetting;
import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.RoomStatus;
import com.training.senla.enums.SortType;
import com.training.senla.facade.Facade;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.dao.GuestDao;
import com.training.senla.dao.RegistrationDao;
import com.training.senla.dao.RoomDao;
import com.training.senla.dao.ServiceDao;
import com.training.senla.service.GuestService;
import com.training.senla.service.RegistrationService;
import com.training.senla.service.RoomService;
import com.training.senla.service.ServiceService;
import com.training.senla.util.io.exporter.Exporter;
import com.training.senla.util.io.exporter.impl.ExporterImpl;
import com.training.senla.util.io.importer.Importer;
import com.training.senla.util.service.StreamService;

import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 13.10.16.
 */
public class FacadeImpl implements Facade {

    private GuestService guestService;
    private RoomService roomService;
    private RegistrationService registrationService;
    private ServiceService serviceService;

    private Importer importer;
    private Exporter exporter;

    private StreamService streamService;

    @Override
    public void init() {
        fillServices();
        streamService = (StreamService) DependencyInjection.getInstance(StreamService.class);
        importer = (Importer) DependencyInjection.getInstance(Importer.class);
        exporter = new ExporterImpl();
    }

    private void fillServices() {
        GuestDao guestDao = (GuestDao) DependencyInjection.getInstance(GuestDao.class);
        RoomDao roomDao = (RoomDao) DependencyInjection.getInstance(RoomDao.class);
        RegistrationDao registrationDao = (RegistrationDao) DependencyInjection.getInstance(RegistrationDao.class);
        ServiceDao serviceDao = (ServiceDao) DependencyInjection.getInstance(ServiceDao.class);
        guestService = (GuestService) DependencyInjection.getInstance(GuestService.class);
        guestService.setGuestDao(guestDao);

        roomService = (RoomService) DependencyInjection.getInstance(RoomService.class);
        roomService.setGuestDao(guestDao);
        roomService.setRoomDao(roomDao);
        roomService.setRegistrationDao(registrationDao);

        registrationService = (RegistrationService) DependencyInjection.getInstance(RegistrationService.class);
        registrationService.setRegistrationRepository(registrationDao);

        serviceService = (ServiceService) DependencyInjection.getInstance(ServiceService.class);
        serviceService.setServiceDao(serviceDao);
    }

    @Override
    public Guest getGuest(int id) {
        Guest guest = null;
        synchronized (guestService) {
            guest = guestService.getGuest(id);
        }
        return guest;
    }

    @Override
    public void addGuest(Guest guest) {
        synchronized (guestService) {
            guestService.addGuest(guest);
        }
    }

    @Override
    public void updateGuest(Guest guest) {
        synchronized (guestService) {
            guestService.update(guest);
        }
    }

    @Override
    public Room getRoom(int id) {
        Room room = null;
        synchronized (roomService) {
            room = roomService.getRoom(id);
        }
        return room;
    }

    @Override
    public void addRoom(Room room) {
        synchronized (roomService) {
            roomService.addRoom(room);
        }
    }

    @Override
    public Registration getRegistration(int id) {
        Registration registration = null;
        synchronized (registrationService) {
            registration = registrationService.getRegistration(id);
        }
        return registration;
    }

    @Override
    public Service getService(int id) {
        Service service = null;
        synchronized (serviceService) {
            service = serviceService.getService(id);
        }
        return service;
    }

    @Override
    public void updateService(Service service) {
        synchronized (serviceService) {
            serviceService.update(service);
        }
    }

    @Override
    public void addService(Service service) {
        synchronized (serviceService) {
            serviceService.addService(service);
        }
    }

    @Override
    public void updateRegistration(Registration registration) {
        synchronized (registrationService) {
            registrationService.update(registration);
        }
    }

    @Override
    public void addRegistration(Registration registration) {
        synchronized (registrationService) {
            registrationService.addRecord(registration);
        }
    }

    @Override
    public List<Guest> getAllGuests() {
        List<Guest> guests = null;
        synchronized (guestService) {
            guests = guestService.getAll(null);
        }
        return guests;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> rooms = null;
        synchronized (roomService) {
            rooms = roomService.getAll(null);
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByPrice() {
        List<Room> rooms = null;
        synchronized (roomService) {
            rooms = roomService.getAll(SortType.price);
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByCapacity() {
        List<Room> rooms = null;
        synchronized (roomService) {
            rooms = roomService.getAll(SortType.capacity);
        }
        return rooms;
    }

    @Override
    public List<Room> getSortedByRating() {
        List<Room> rooms = null;
        synchronized (roomService) {
            rooms = roomService.getAll(SortType.rating);
        }
        return rooms;
    }

    @Override
    public List<Room> getAllFreeRooms() {
        List<Room> rooms = null;
        synchronized (roomService) {
            rooms = roomService.getAllFree(null);
        }
        return rooms;
    }

    @Override
    public List<Guest> getGuestsRoom() {
        List<Guest> guests = null;
        synchronized (guestService) {
            guests = guestService.getAll(null);
        }
        return guests;
    }

    @Override
    public void updateRoom(Room room) {
        synchronized (roomService) {
            roomService.update(room);
        }
    }

    @Override
    public int getCountFreeRooms() {
        int count = 0;
        synchronized (roomService) {
            count = roomService.getCountFreeRooms();
        }
        return count;
    }

    @Override
    public int getCountGuests() {
        int count = 0;
        synchronized (guestService) {
            count = guestService.getCount();
        }
        return count;
    }

    @Override
    public List<Room> getReleasedRoomsInFuture(Date date) {
        List<Room> rooms = null;
        synchronized (roomService) {
            rooms = roomService.getReleasedInFuture(date);
        }
        return rooms;
    }

    @Override
    public double getSumPaymentRoom(Guest guest, Room room) {
        double sum = 0;
        synchronized (registrationService) {
            sum = registrationService.getSumByRoom(room, guest);
        }
        return sum;
    }

    @Override
    public List<Service> getGuestServices(Guest guest) {
        List<Service> services = null;
        synchronized (serviceService) {
            services = serviceService.getServices(guest, SortType.price);
        }
        return services;
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = null;
        synchronized (serviceService) {
            services = serviceService.getAll(null);
        }
        return services;
    }

    @Override
    public List<Guest> getSortedByFinalDate() {
        List<Guest> guests = null;
        synchronized (registrationService) {
            guests = registrationService.getSortedByFinalDate();
        }
        return guests;
    }

    @Override
    public List<Double> getPricesRoom() {
        return null;
    }

    @Override
    public void registerGuest(Guest guest, Room room, Date startDate, Date finalDate) {
        synchronized (roomService) {
            roomService.registerGuest(guest, room, startDate, finalDate);
        }
    }

    @Override
    public void evictGuest(Guest guest) {
        synchronized (roomService) {
            roomService.evictGuest(guest);
        }
    }


    @Override
    public void changeServicePrice(Service service, double price) {
        service.setPrice(price);
        synchronized (serviceService) {
            serviceService.update(service);
        }
    }

    @Override
    public void changeRoomPrice(Room room, double price) {
        room.setPrice(price);
        synchronized (roomService) {
            roomService.update(room);
        }
    }

    @Override
    public boolean changeRoomStatus(Room room) {
        if (ClassSetting.getProps().isBlockStatus()) {
            room.setStatus(RoomStatus.MAINTAINED);
            synchronized (roomService) {
                roomService.update(room);
            }
            return true;
        }
        return false;
    }

    @Override
    public void cloneRoom(int id) {
        Room room = null;
        synchronized (roomService) {
            room = roomService.cloneRoom(id);
            roomService.addRoom(room);
        }
    }

    @Override
    public List<Registration> getAllRegistrations() {
        List<Registration> registrations = null;
        synchronized (registrationService) {
            registrations = registrationService.getAll(null);
        }
        return registrations;
    }

    @Override
    public void importGuests() {
        synchronized (guestService) {
            importer.importGuests(this.guestService.getAll(null));
        }
    }

    @Override
    public void importRegistrations() {
        synchronized (registrationService) {
            importer.importRegistrations(this.registrationService.getAll(null));
        }
    }

    @Override
    public void importRooms() {
        synchronized (roomService) {
            importer.importRooms(this.roomService.getAll(null));
        }
    }

    @Override
    public void importServices() {
        synchronized (serviceService) {
            importer.importServices(this.serviceService.getAll(null));
        }
    }

    @Override
    public void exportGuests() {
        synchronized (guestService) {
            exporter.exportCollection(this.guestService.getAll(null), Guest.class);
        }
    }

    @Override
    public void exportRegistrations() {
        synchronized (registrationService) {
            exporter.exportCollection(this.registrationService.getAll(null), Registration.class);
        }
    }

    @Override
    public void exportRooms() {
        synchronized (roomService) {
            exporter.exportCollection(this.roomService.getAll(null), Room.class);
        }
    }

    @Override
    public void exportServices() {
        synchronized (serviceService) {
            exporter.exportCollection(this.serviceService.getAll(null), Service.class);
        }
    }

    @Override
    public void exportAll() {
        exporter.exportAll(this.getAllGuests(), this.getAllRegistrations(), this.getAllRooms(), this.getAllServices());
    }

    public StreamService getStreamService() {
        return streamService;
    }
}
