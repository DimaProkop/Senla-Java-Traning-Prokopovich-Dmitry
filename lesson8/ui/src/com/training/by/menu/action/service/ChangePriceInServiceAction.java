package com.training.by.menu.action.service;

import com.training.by.menu.action.Action;
import com.training.by.print.PrintModel;
import com.training.by.reader.Reader;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.ServiceModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class ChangePriceInServiceAction implements Action {
    private static final Logger LOG = LogManager.getLogger(ChangePriceInServiceAction.class);

    @Override
    public void execute() {
        int serviceId = Reader.getInt("Input service ID: ");
        try {
            ServiceModel service = FacadeImpl.getInstance().getService(serviceId);
            if(service == null) {
                PrintModel.printMessage("Service not found.");
            }else {
                double value = Reader.getDouble("Input new price for service: ");
                FacadeImpl.getInstance().changeServicePrice(service, value);
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
