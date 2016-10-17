package com.training.senla.comparator;
import com.training.senla.model.RoomModel;

import java.util.Comparator;

/**
 * Created by prokop on 14.10.16.
 */
public class RoomRatingComparator implements Comparator<RoomModel>{
    @Override
    public int compare(RoomModel roomModel, RoomModel t1) {
        return Integer.compare(roomModel.getRating(), t1.getRating());
    }
}
