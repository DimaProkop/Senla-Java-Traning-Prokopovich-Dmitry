package com.training.by.menu.action.registration;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.senla.DataPacket;
import com.training.senla.RequestHandler;
import com.training.senla.model.Registration;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class AllRegistrationsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(AllRegistrationsAction.class);

    @Override
    public void execute(RequestHandler requestHandler) {
        try {
            DataPacket packet = new DataPacket("getAllRegistrations", null);
            List<Registration> registrations = (List<Registration>) requestHandler.sendRequest(packet);
            if(registrations == null || registrations.size() == 0) {
                PrintModel.printMessage("Registrations not found.");
            }else {
                PrintModel.printRegistrations(registrations);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
