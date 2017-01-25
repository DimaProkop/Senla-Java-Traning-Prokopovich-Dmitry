package com.training.senla.menu.action.service;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.enums.ServicesSection;
import com.training.senla.model.Service;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class NewServiceAction implements Action {
    private static final Logger LOG = LogManager.getLogger(NewServiceAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            List<Object> objects = new ArrayList<>();
            String name = Reader.getString("Input service name: ");
            double price = Reader.getDouble("Input price: ");
            String strSection = Reader.getString("Input room section: ");
            ServicesSection section = ServicesSection.isExist(strSection.toUpperCase());
            Date startDate = Reader.getDate("Input start date - (dd-mm-yyyy): ");
            Date finalDate = Reader.getDate("Input final date - (dd-mm-yyyy): ");
            Service service = new Service(name, price, section, startDate, finalDate);
            objects.add(service);
            DataPacket packet = new DataPacket("addService", objects);
            requestHandler.sendRequest(packet);
            PrintModel.printMessage("Service created.");
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
