package com.training.senla.comparator;
import com.training.senla.model.RoomModel;

import java.util.Comparator;
/**
 * Created by prokop on 14.10.16.
 */
public class RoomPriceComparator implements Comparator<RoomModel> {
    @Override
    public int compare(RoomModel roomModel, RoomModel t1) {
        return Double.compare(roomModel.getPrice(), t1.getPrice());
    }
}
