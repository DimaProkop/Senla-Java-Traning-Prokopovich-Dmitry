package com.training.senla.comparator;

import java.util.Comparator;
import com.training.senla.model.GuestModel;

/**
 * Created by prokop on 13.10.16.
 */
public class GuestIdComparator implements Comparator<GuestModel>{
    @Override
    public int compare(GuestModel guestModel, GuestModel t1) {
        return Integer.compare(guestModel.getGuestId(), t1.getGuestId());
    }
}
