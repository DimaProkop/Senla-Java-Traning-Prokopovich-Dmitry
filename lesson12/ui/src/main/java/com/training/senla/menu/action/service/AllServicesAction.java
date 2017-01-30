package com.training.senla.menu.action.service;

import com.training.senla.menu.action.Action;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.print.PrintModel;
import com.training.senla.model.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * Created by prokop on 26.10.16.
 */
public class AllServicesAction implements Action {
    private static final Logger LOG = LogManager.getLogger(AllServicesAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getAllServices", null);
            List<Service> services = (List<Service>) requestHandler.sendRequest(packet);
            if(services == null) {
                PrintModel.printMessage("Services not found.");
            }else {
                PrintModel.printServices(services);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
