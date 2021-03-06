package com.training.senla.menu.action.service;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.reader.Reader;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.model.Service;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangePriceInServiceAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ChangePriceInServiceAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        int serviceId = Reader.getInt("Input service ID: ");
        List<Object> objects = new ArrayList<>();
        try {
            objects.add(serviceId);
            DataPacket packet = new DataPacket("getService", objects);
            Service service = (Service) requestHandler.sendRequest(packet);
            if(service == null) {
                PrintModel.printMessage("Service not found.");
            }else {
                double value = Reader.getDouble("Input new price for service: ");
                objects.clear();
                objects.add(service);
                objects.add(value);
                packet = new DataPacket("changeServicePrice", objects);
                requestHandler.sendRequest(packet);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
