package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.enums.RoomsSection;
import com.training.senla.enums.ServicesSection;
import com.training.senla.facade.Facade;
import com.training.senla.model.Guest;
import com.training.senla.model.Registration;
import com.training.senla.model.Room;
import com.training.senla.model.Service;
import com.training.senla.util.connection.ConnectionManager;
import com.training.senla.util.db.LibraryQueries;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import static com.training.senla.util.db.ParserResultSet.*;

/**
 * Created by prokop on 12.10.16.
 */


public class ClassForTest {

    public static Connection connection;
    public static String username = "root";
    public static String password = "root";
    public static String URL = "jdbc:mysql://localhost:3306/hotel";

    public static String TOKEN = "'";
    public static String DELIMITER = ", ";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Facade facade = (Facade) DependencyInjection.getInstance(Facade.class);
        facade.init();

        LibraryQueries libraryQueries = new LibraryQueries();
//        System.out.println(facade.getReleasedRoomsInFuture(new Date(117, 6, 2)));

        connection = ConnectionManager.getInstance().getConnection();
        PreparedStatement statement = null;
        List<Room> rooms = new ArrayList<>();
        try {
            String currentDate = libraryQueries.formatter.format(new Date(117, 6, 2));
            statement = connection.prepareStatement("SELECT COUNT(*) FROM registration WHERE finalDate < STR_TO_DATE('2017-02-03', '%Y-%m-%d')");
            ResultSet set = statement.executeQuery();
            int count = 0;
            while (set.next()) {
                count = set.getInt(1);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }



        ConnectionManager.getInstance().closeConnection();

    }

    private static final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-dd-MM");

    public static void printGuest(Guest guest) {
        System.out.printf("id:    name:      room:\n");
        System.out.printf("%d       %s        %s\n", guest.getId(), guest.getName(), guest.getRoom() == null ? "does't have room" : String.valueOf(guest.getRoom().getId()));
    }

    public static void printGuests(List<Guest> guests) {
        guests.forEach(ClassForTest::printGuest);
    }

    public static void printRoom(Room room) {
        System.out.printf("id:    price:      capacity:    status:       section:       rating:\n");
        System.out.printf("%d    %f      %d          %s          %s          %d\n", room.getId(), room.getPrice(), room.getCapacity(), room.getStatus().toString(), room.getSection().toString(), room.getRating());
    }

    public static void printRooms(List<Room> rooms) {
        rooms.forEach(ClassForTest::printRoom);
    }

    public static void printService(Service service) {
        System.out.printf("id:    name:      price:      section:        startDate:      finalDate:\n");
        System.out.printf("%d    %s      %f    %s      %s      %s\n", service.getId(), service.getName(), service.getPrice(), service.getSection().toString(), formatter.format(service.getStartDate()), formatter.format(service.getFinalDate()));
    }

    public static void printServices(List<Service> services) {
        services.forEach(ClassForTest::printService);
    }

    public static void printRegistration(Registration registration) {
        System.out.printf("id:   guestId:    roomId:    startDate:      finalDate:\n");
        System.out.printf("%d   %d    %d    %s      %s\n", registration.getId(), registration.getGuestId(), registration.getRoomId(), formatter.format(registration.getStartDate()), formatter.format(registration.getFinalDate()));
    }

    public static void printRegistrations(List<Registration> registrations) {
        registrations.forEach(ClassForTest::printRegistration);
    }

}
