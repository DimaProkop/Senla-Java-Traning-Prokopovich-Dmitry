package com.training.senla.comparator;

import com.training.senla.model.RegistrationModel;

import java.util.Comparator;
/**
 * Created by prokop on 18.10.16.
 */
public class GuestRoomDataComparator implements Comparator<RegistrationModel>{
    @Override
    public int compare(RegistrationModel registrationModel, RegistrationModel t1) {
        return registrationModel.getFinalDate().compareTo(t1.getFinalDate());
    }
}
