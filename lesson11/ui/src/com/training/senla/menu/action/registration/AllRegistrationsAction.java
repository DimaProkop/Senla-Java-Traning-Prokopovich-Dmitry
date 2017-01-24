package com.training.senla.menu.action.registration;

import com.training.senla.menu.action.Action;
import com.training.senla.print.PrintModel;
import com.training.senla.service.DataPacket;
import com.training.senla.service.RequestHandler;
import com.training.senla.model.RegistrationModel;
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
            List<RegistrationModel> registrations = (List<RegistrationModel>) requestHandler.sendRequest(packet);
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
