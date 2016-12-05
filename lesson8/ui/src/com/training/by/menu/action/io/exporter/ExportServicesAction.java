package com.training.by.menu.action.io.exporter;

import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.senla.model.ServiceModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 7.11.16.
 */
public class ExportServicesAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ExportServicesAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getAllServices", null);
            List<ServiceModel> services = (List<ServiceModel>) requestHandler.sendRequest(packet);
            if (services.size() == 0) {
                PrintModel.printMessage("Services is missing.");
            } else {
                packet = new DataPacket("exportServices", null);
                requestHandler.sendRequest(packet);
                PrintModel.printMessage("Services have successfully exported.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
