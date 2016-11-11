package com.training.by.menu.action.service;

import com.training.by.menu.action.Action;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.senla.model.ServiceModel;
import com.training.by.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by prokop on 26.10.16.
 */
public class AllServicesAction implements Action {
    private static final Logger LOG = LogManager.getLogger(AllServicesAction.class);

    @Override
    public void execute() {
        try {
            List<ServiceModel> services = FacadeImpl.getInstance().getAllServices();
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
