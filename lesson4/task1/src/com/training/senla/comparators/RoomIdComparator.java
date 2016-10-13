package com.training.senla.comparators;

import com.training.senla.models.RoomModel;

import java.util.Comparator;

/**
 * Created by prokop on 13.10.16.
 */
public class RoomIdComparator implements Comparator<RoomModel>{
    @Override
    public int compare(RoomModel roomModel, RoomModel t1) {
        return Integer.compare(roomModel.getRoomId(), t1.getRoomId());
    }
}
