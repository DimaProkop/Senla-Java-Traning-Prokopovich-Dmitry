package com.training.senla.comparator;

import com.training.senla.model.GuestModel;

import java.util.Comparator;

/**
 * Created by prokop on 14.10.16.
 */
public class GuestNameComparator implements Comparator<GuestModel> {
    @Override
    public int compare(GuestModel guestModel, GuestModel t1) {
        return guestModel.getName().compareTo(t1.getName());
    }
}
