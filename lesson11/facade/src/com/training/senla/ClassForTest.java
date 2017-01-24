package com.training.senla;

import com.training.senla.di.DependencyInjection;
import com.training.senla.facade.Facade;
import com.training.senla.model.GuestModel;
import com.training.senla.model.RegistrationModel;
import com.training.senla.model.RoomModel;
import com.training.senla.model.ServiceModel;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.training.senla.util.ParserResultSet.*;

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

        Class.forName("com.mysql.cj.jdbc.Driver");


        connection = DriverManager.getConnection(URL, username, password);




        Statement statement = null;
        List<RegistrationModel> registrations = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM registration ORDER BY id");
            while (set.next()) {
                registrations.add(parseRegistration(statement, set));
            }
            set.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        printRegistrations(registrations);



    }

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    public static void printGuest(GuestModel guest) {
        System.out.printf("id:    name:      room:\n");
        System.out.printf("%d       %s        %s\n", guest.getId(), guest.getName(), guest.getRoomModel() == null ? "does't have room" : String.valueOf(guest.getRoomModel().getId()));
    }

    public static void printGuests(List<GuestModel> guests) {
        guests.forEach(ClassForTest::printGuest);
    }

    public static void printRoom(RoomModel room) {
        System.out.printf("id:    price:      capacity:    status:       section:       rating:\n");
        System.out.printf("%d    %f      %d          %s          %s          %d\n", room.getId(), room.getPrice(), room.getCapacity(), room.getStatus().toString(), room.getSection().toString(), room.getRating());
    }

    public static void printRooms(List<RoomModel> rooms) {
        rooms.forEach(ClassForTest::printRoom);
    }

    public static void printService(ServiceModel service) {
        System.out.printf("id:    name:      price:      section:        startDate:      finalDate:\n");
        System.out.printf("%d    %s      %f    %s      %s      %s\n", service.getId(), service.getName(), service.getPrice(), service.getSection().toString(), formatter.format(service.getStartDate()), formatter.format(service.getFinalDate()));
    }

    public static void printServices(List<ServiceModel> services) {
        services.forEach(ClassForTest::printService);
    }

    public static void printRegistration(RegistrationModel registration) {
        System.out.printf("id:   guestId:    roomId:    startDate:      finalDate:\n");
        System.out.printf("%d   %d    %d    %s      %s\n", registration.getId(), registration.getGuestId(), registration.getRoomId(), formatter.format(registration.getStartDate()), formatter.format(registration.getFinalDate()));
    }

    public static void printRegistrations(List<RegistrationModel> registrations) {
        registrations.forEach(ClassForTest::printRegistration);
    }

}
