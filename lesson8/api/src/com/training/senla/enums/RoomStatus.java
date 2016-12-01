package com.training.senla.enums;

/**
 * Created by prokop on 13.10.16.
 */
public enum  RoomStatus {
    MAINTAINED,
    FREE,
    BUSY;

    public static com.training.senla.enums.RoomStatus isExist(String string) {
        for(com.training.senla.enums.RoomStatus status : values()) {
            if(status.toString().equals(string)) {
                return status;
            }
        }
        return null;
    }
}
