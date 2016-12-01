package com.training.senla.enums;

/**
 * Created by prokop on 13.10.16.
 */
public enum RoomsSection{
    SINGLE,
    STANDART,
    IMPROVED,
    LUKS;

    public static com.training.senla.enums.RoomsSection isExist(String string) {
        for(com.training.senla.enums.RoomsSection section : values()) {
            if(section.toString().equals(string)) {
                return section;
            }
        }
        return null;
    }
}
