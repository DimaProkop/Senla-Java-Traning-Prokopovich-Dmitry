package com.training.by.menu.action.io.exporter;

import com.training.by.menu.action.Action;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.by.print.PrintModel;
import com.training.senla.model.RegistrationModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 7.11.16.
 */
public class ExportRegistrationsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ExportRegistrationsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getAllRegistrations", null);
            List<RegistrationModel> registrations = (List<RegistrationModel>) requestHandler.sendRequest(packet);
            if (registrations.size() == 0) {
                PrintModel.printMessage("Registrations is missing.");
            } else {
                packet = new DataPacket("exportRegistrations", null);
                requestHandler.sendRequest(packet);
                PrintModel.printMessage("Registrations have successfully exported.");
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
