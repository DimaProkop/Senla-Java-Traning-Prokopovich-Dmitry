package com.training.by.menu.action.guest;

import com.training.by.menu.action.Action;
import com.training.senla.facade.impl.FacadeImpl;
import com.training.by.print.PrintModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by prokop on 26.10.16.
 */
public class CountGuestsAction implements Action {
    private static final Logger LOG = LogManager.getLogger(CountGuestsAction.class);

    @Override
    public void execute() {
        try {
            int count = FacadeImpl.getInstance().getCountGuests();
            if(count == 0) {
                PrintModel.printMessage("Guests not found.");
            }else {
                PrintModel.printMessage(String.valueOf(count));
            }
        }catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }
}
